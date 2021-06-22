package main
// 패키지 선언
// main 패키지는 프로그램의 시작점 역할을 한다.
// 모든 실행 가능한 Go 프로그램에는 main 패키지가 있고, 프로그램을 구동할 때, main 패키지의 main 함수를 찾아 실행한다.
import "fmt"
// 외부 패키지 임포트

func main(){ // main 함수는 Go 프로그램의 시작점이다.
	fmt.Println("Hello World!")
	// 외부 패키지 사용
}
