import java.util.*;
/*
기억한 멜로디를 재생 시간과 제공된 악보를 직접 보며 비교
음악제목 재생시작 재생끝난시간 악보(음악의시간을 알 수 있겠군)
음악은 반드시 처음부터 재생
    재생시간이 음악보다 길면 처음부터 반복 재생
조건이 일치하는 음약 => 재생된 시간이 제일 긴 음악 반환 => 먼저 입력된 음악
없으면 (None)

슬라이딩윈도우일듯?
악보를 배열로바꿔야겠네
악보시간과 재생시간으로 재생된악보를 생성
주어진 시간문자열에서 재생 시간 추출
*/
class Solution {
    
    class PlayedMusic{
        String title;
        String playedMusic;
        int playedTime;
        
        public PlayedMusic(String title, String playedMusic, int playedTime){
            this.title=title;
            this.playedMusic=playedMusic;
            this.playedTime=playedTime;
        }
    }
    
    public String solution(String m, String[] musicinfos) {
        
        m = replaceChord(m);
        int maxPlayed = -1;
        String answer = "";
        for(String musicinfo:musicinfos){
            PlayedMusic playedMusic = playedMusic(musicinfo);
            System.out.println(playedMusic.playedMusic);
            if(playedMusic.playedMusic.contains(m) && playedMusic.playedTime > maxPlayed){
                answer = playedMusic.title;
                maxPlayed = playedMusic.playedTime;
            }
        }
        
        if(maxPlayed == -1){
            answer = "(None)";
        }
        return answer;
    }
    // 주어진 문자열에서 정보 추출 후 재생된 악보 반환 함수
    public PlayedMusic playedMusic(String musicinfo){  // 제목과 재생된 악보 리턴
        String[] mi = musicinfo.split(",");
        int playedTime = returnMinutes(mi[1]) - returnMinutes(mi[0]);
        String music = replaceChord(mi[3]);
        
        //if(playedTime > music.length()){
            StringBuilder newMusic = new StringBuilder();
            
            for (int i = 0; i < playedTime / music.length(); i++)
                newMusic.append(music);
                
            newMusic.append(music.substring(0, playedTime % music.length()));
            music = newMusic.toString();
       // }
        
        return new PlayedMusic(mi[2], music, playedTime);
    }
    
    // 시간 추출 함수
    public int returnMinutes(String time){
        String[] times = time.split(":");
        return Integer.parseInt(times[0])*60 + Integer.parseInt(times[1]);
    }
    
    public String replaceChord(String music){
        //C, C#, D, D#, E, F, F#, G, G#, A, A#, B
        //    H     I          J     K      L
        music = music.replaceAll("C#","H")
            .replaceAll("D#","I")
            .replaceAll("F#","J")
            .replaceAll("G#","K")
            .replaceAll("A#","L")
            .replaceAll("B#", "M");
        
        return music;
    }
}