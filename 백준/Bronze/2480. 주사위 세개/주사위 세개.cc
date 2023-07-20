#include<iostream>
using namespace std;

int main() {

	int a, b, c;
	cin >> a >> b >> c;

	if (a == b && b == c && c == a) {
		cout << 10000 + a * 1000;
	}
	else if (a == b || b == c || c == a) {
		if (a == b)
			cout << 1000 + a * 100;
		if (b == c)
			cout << 1000 + b * 100;
		if (c == a)
			cout << 1000 + c * 100;
	}
	else {
		int max=a;
		if (b > a)
			max = b;
		if (c > b && max < c)
			max = c;
		cout << max * 100;
	}

	return 0;
}