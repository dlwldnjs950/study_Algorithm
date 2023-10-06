import java.util.Arrays;

public class SelectionSort_BubbleSort_MergeSort {

	// (오름차순)
	// 선택정렬, 버블정렬, 병합정렬 
	public static void main(String[] args) {
		
		int dataLength = 100000;
		
		int data[] = new int[dataLength];
		
		randomData(data);
		System.out.println("*** 선택 정렬 ***");
		System.out.println("전 : " + Arrays.toString(data));
		System.out.println("후 : " + Arrays.toString(selectionSort(data)));
		System.out.println();

		randomData(data);
		System.out.println("*** 버블 정렬 ***");
		System.out.println("전 : " + Arrays.toString(data));
		System.out.println("후 : " + Arrays.toString(bubbleSort(data)));
		System.out.println();
		
		randomData(data);
		System.out.println("*** 병합 정렬 ***");
		System.out.println("전 : " + Arrays.toString(data));
		System.out.println("후 : " + Arrays.toString(mergeSort(data)));
		System.out.println();
	}
	
	public static void randomData(int[] data) {
		for(int idx = 0; idx<data.length; idx++) {
			data[idx] = (int) (Math.random() * 100 + 1);
		}
	}

	// 선택 정렬 : 찾은 최소값을 넣을 포인터, 그 이하에서 최소값 찾기
	public static int[] selectionSort(int[] data) {
		
		// pointer : 찾은 최솟값을 넣을 자리
		for(int pointer = 0; pointer < data.length; pointer++) {
			
			// pointer가 가리키는 곳 이하에서 최솟값을 찾는다
			int min = Integer.MAX_VALUE;
			int minIdx = -1;
			
			for(int idx = pointer; idx < data.length; idx++) {
				
				// 찾기 (인덱스와 값)
				if(data[idx] < min) {
					min = data[idx];
					minIdx = idx;
				}
			}
			
			// 찾은 최솟값 자리 바꾸기
			int tmp = data[pointer];
			data[pointer] = data[minIdx];
			data[minIdx] = tmp;
		}
		
		return data;
	}
	
	// 버블 정렬 : 두 개 씩 비교. 각 반복에 가장 큰 수가 뒷쪽에 위치(살펴볼 마지막 범위)
	public static int[] bubbleSort(int[] data) {
		
		// 찾은 최댓값이 위치할 인덱스
		for(int lastIdx = data.length; lastIdx>0; lastIdx--) {
			
			// 두개씩 값 비교하기
			for(int idx = 1; idx < lastIdx; idx++) {
				
				// 오름차순 정렬이기 때문에
				// 좌측값이 우측보다 크면 자리를 바꾼다.
				if(data[idx-1] > data[idx]) {
					int tmp = data[idx-1];
					data[idx-1] = data[idx];
					data[idx] = tmp;
				}
			}
		}
		
		return data;
	}
	
	// 병합 정렬 : 1개가 될때 까지 나누고 -> 합치기 (정렬하며(각 배열의 맨앞부터))
	// 나누기 : 실제로 배열 두개로 나누기 X, 시작 인덱스 & 길이
	// 합치기 : 정렬 수행
	//		중간 정렬 결과를 저장할 배열이 추가로 필요
	
	static int[] result;
	public static int[] mergeSort(int[] data) {

		result = new int[data.length];
		divide(data, 0, data.length - 1);
		
		return data;
	}

	// 나눌 배열, 나눌 배열의 시작과 마지막 인덱스
	public static void divide(int[] data, int left, int right) {

		// 시작 인덱스가 마지막 인덱스보다 왼쪽에 있는 동안 나눈다
		if (left < right) {
			int mid = (left + right) / 2;
			divide(data, left, mid);
			divide(data, mid + 1, right);
			merge(data, left, right);
		}
	}

	// 합치기
	public static void merge(int[] data, int left, int right) {
		int mid = (left + right)/2;
		int leftIdx = left;
		int rightIdx = mid +1;
		int resultIdx = left;
		
		// 나뉜 배열 양쪽을 살펴 보면서
		while(leftIdx <= mid && rightIdx <= right) {
			
			// 왼쪽 데이터가 더 크다면
			if(data[leftIdx] > data[rightIdx]) {
				
				// 결과 배열에 오른쪽 값
				result[resultIdx++] = data[rightIdx++];
			}else {
				
				// 왼쪽 데이터가 더 작다면 
				// 결과배열에 오른쪽 값
				result[resultIdx++] = data[leftIdx++];
			}
		}
		
		// 왼쪽 배열이 다 비워졌는데
		// 오른쪽 배열에 아직 값이 남아있다면 결과 배열에 담기
		if(leftIdx > mid) {
			while(rightIdx <= right) {
				result[resultIdx++] = data[rightIdx++];
			}
		}
		// 오른쪽 배열이 다 비워졌는데
		// 왼쪽 배열에 아직 값이 남아있다면 결과 배열에 담기
		else {
			while(leftIdx <= mid) {
				result[resultIdx++] = data[leftIdx++];
			}
		}
		
		// 결과배열이 다 만들어 졌으면 원본 배열에 반영하기
		for(int idx = left; idx<= right; idx++) {
			data[idx] = result[idx];
		}
	}
}
