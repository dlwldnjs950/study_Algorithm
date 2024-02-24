import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	/*
	 * 가능한 방이 없으면 새로운 방
	 * 	처음 입장한 플레이어 기준 +/- 10
	 * 입장 가능한 방이 여러 개면 먼저 생성된 방
	 * 방의 정원
	 * 
	 * 플레이어 수, 방의 정원
	 * 각 플레이어의 레벨, 닉네임
	 * 
	 * 방의 상태
	 * 각 방의 플레이어
	 * 		닉넴 사전순
	 * */

	static BufferedReader br;
	static StringBuilder sb;
	static StringTokenizer st;

	public static class Room {
		int referLevel;
		PriorityQueue<Player> players;

		public Room(Player player) {
			this.referLevel = player.level;
			players = new PriorityQueue<Player>(new Comparator<Player>() {

				@Override
				public int compare(Player o1, Player o2) {

					return o1.nickname.compareTo(o2.nickname);
				}
			});

			players.add(player);
		}
	}

	public static class Player {
		int level;
		String nickname;

		public Player(int level, String nickname) {
			this.level = level;
			this.nickname = nickname;
		}

		@Override
		public String toString() {
			return level + " " + nickname;
		}

	}

	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();

		// 플레이어 수, 방의 정원
		st = new StringTokenizer(br.readLine().trim());
		int playerNum = Integer.parseInt(st.nextToken());
		int playerLimit = Integer.parseInt(st.nextToken());

		List<Room> rooms = new ArrayList<Room>();

		for (int idx = 0; idx < playerNum; idx++) {
			st = new StringTokenizer(br.readLine().trim());
			int level = Integer.parseInt(st.nextToken());
			String nickname = st.nextToken();

			Player newPlayer = new Player(level, nickname);

			int roomNum = findRoom(rooms, level, playerLimit);

			// 입장 가능한 방이 없으면 새로 만들고
			if (roomNum == -1) {
				rooms.add(new Room(newPlayer));
			}
			// 아니면 입장
			else {
				Room room = rooms.get(roomNum);
				room.players.add(newPlayer);
			}
		}

		for (Room room : rooms) {
			if (room.players.size() < playerLimit)
				sb.append("Waiting!");
			else
				sb.append("Started!");
			sb.append("\n");
			
			// 정렬된 순서대로 출력
			while(!room.players.isEmpty()) {
				sb.append(room.players.poll().toString()).append("\n");
			}
		}

		System.out.println(sb);

	}

	public static int findRoom(List<Room> rooms, int level, int playerLimit) {
		for (int idx = 0; idx < rooms.size(); idx++) {
			Room room = rooms.get(idx);

			// 아직 정원이 안찼고, 입장 가능한 레벨이면
			if (room.players.size() < playerLimit && room.referLevel - 10 <= level && level <= room.referLevel + 10)
				return idx;
		}
		return -1;
	}

}
