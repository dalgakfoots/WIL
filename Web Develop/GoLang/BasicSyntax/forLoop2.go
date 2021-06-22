package main

import "fmt"

func main(){
	sum, i := 0, 0
	for i < 10 { // 조건식만 사용
		sum += i
		i++
	}
	fmt.Println(sum)
}