package main

import "fmt"

func main(){
	sum, i := 0, 0
	for { // for 문에 조건식 생략
		if i>= 10{
			break
		}
		sum+=i
		i++
	}
	fmt.Println(sum)
}
