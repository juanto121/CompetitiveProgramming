#include<iostream>
#include<cmath>

using namespace std;
const double PI = 4*atan(1);
const double m = 1e-9;


typedef struct point{
	double x;
	double y;
} point;

void printarr(double arr[1], int n){
	cout << "Array: \n";
	for(int i = 0; i < n; i++){
		cout << arr[i] << " " << endl;
	}
}

int main(){
	int N;
	while(scanf("%d",&N),N){
		double camera_x,camera_y, camera_fov;
		camera_fov = 0.0;
		scanf("%lf %lf %lf",&camera_x, &camera_y, &camera_fov);
		point camera = {camera_x,camera_y};
		double angles[N];
		for(int i = 0; i < N ; i++){
			double p2x, p2y;
			scanf("%lf %lf", &p2x, &p2y);
			// [-pi,pi] -> 0-360, (90 - deg + 360) => 0.0 @ Y axis clockwise.
			angles[i] = fmod(PI/2 - fmod( atan2( p2y - camera.y, p2x-camera.x) + 2*PI , 2*PI )+ 2 * PI , 2 * PI) * 180 / PI;		
		//	cout << angles[i] << endl;
		}
	//	printarr(angles, N);
		int max = 0;
		double max_angle = 0.0;
		for(double i = 359.9; i + m >= 0.0; i-= 0.1){
			int count = 0;
			for(int j = 0; j < N ; j++){
				double lower = i - camera_fov / 2.0;
				double upper = i + camera_fov /2.0;
				
				if(lower< 0){
					lower += 360;
				}	else if(upper > 360){
					upper -= 360;
				}			
				
				if( upper < lower )
				{
					if( upper  > angles[j]  || lower  < angles[j] ) count ++;
				}else{
					if( lower   < angles[j]  && upper > angles[j]  ){
						count ++;
					}	
				}
			}
			if(count != 0.0 && count >= max ){
				max = count;
				max_angle = i;
			}
		}
		printf("Point the camera at angle %.1lf to view %d infested trees.\n",fabs( max_angle ), max);
	}
}


