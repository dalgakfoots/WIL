import React, {useState, useEffect} from "react";
import './App.css';

function App() {

    var [funcShow, setFuncShow] = useState(true);
    var [classShow, setClassShow] = useState(true);
  return (
    <div className="container">
      <h1>Hello World</h1>
        <input type="button" value="remove func" onClick={
            function () {
                setFuncShow(false);
            }
        }></input>
        <input type="button" value="remove comp" onClick={
            function () {
                setClassShow(false);
            }
        }></input>
        {funcShow ? <FuncComp initNumber={2}></FuncComp> : null}
        {classShow ? <ClassComp initNumber={2}></ClassComp> : null}
    </div>
  );
}



function FuncComp(props){

    var funcStyle = 'color:blue';
    var funcId = 0;

    var numberState = useState(props.initNumber);
    var number = numberState[0];
    var setNumber = numberState[1];

    var dateState = useState((new Date()).toString());
    var _date = dateState[0];
    var setDate = dateState[1];

    // var [_date , setDate] = useState((new Date()).toString());

    //side effect <-> main effect
    useEffect(function(){
        console.log('%cfunc => useEffect (componentDidMount) ' + (++funcId), funcStyle);
        document.title = number;
        return function () { // clean-up
            console.log('%cfunc => useEffect return (componentWillUnMount) ' + (++funcId), funcStyle);
        }
    }, []); // 빈 배열을 전달 시, componentDidMount 처럼 동작한다.


    //side effect <-> main effect
    useEffect(function(){
        console.log('%cfunc => useEffect number (componentDidMount & componentDidUpdate) ' + (++funcId), funcStyle);
        document.title = number;
        return function () { // clean-up
            console.log('%cfunc => useEffect number return (componentDidMount & componentDidUpdate) ' + (++funcId), funcStyle);
        }
    }, [number]); // 대괄호 안에 있는 state가 변경 되었을 때만 실행 되도록 한다.

    // side effect <-> main effect
    useEffect(function(){
        console.log('%cfunc => useEffect date (componentDidMount & componentDidUpdate) ' + (++funcId), funcStyle);
        document.title = _date;
        return function () { // clean-up
            console.log('%cfunc => useEffect _date return (componentDidMount & componentDidUpdate) ' + (++funcId), funcStyle);
        }
    }, [_date]);

    console.log('%cfunc => render ' + (++funcId), funcStyle);
    return (
    <div className="container">
      <h2>function style component</h2>
      <p>
          Number : {number}
      </p>
        <p>Date : {_date}</p>
      <input type="button" value="random" onClick={
            function () {
                setNumber(Math.random());
            }
      }/>
        <input type="button" value="date" onClick={
            function () {
                setDate((new Date()).toString());
            }
        }/>

    </div>
  );
}

class ClassComp extends React.Component{

    state = {
        number : this.props.initNumber,
        date : (new Date()).toString()
    }

    classStyle = 'color:red'
    componentWillMount() {
        console.log("%cclass => componentWillMount", this.classStyle);
    }


    componentDidMount() {
        console.log("%cclass => componentDidMount", this.classStyle);
    }

    shouldComponentUpdate(nextProps, nextState, nextContext) {
        console.log("%cclass => shouldComponentUpdate", this.classStyle);
        return true;
    }

    componentWillUpdate(nextProps, nextState, nextContext) {
        console.log("%cclass => componentWillUpdate", this.classStyle);
    }

    componentWillUnmount() {
        console.log("%cclass => componentWillUnMount", this.classStyle);
    }

    render() {
        console.log("%cclass => render", this.classStyle);
    return (
      <div className="container">
        <h2>class style component</h2>
        <p>
          Number : {this.state.number}
        </p>
        <p>
            Date : {this.state.date}
        </p>
          <input type="button" value="random" onClick={
              function () {
                  this.setState({number : Math.random()})
              }.bind(this)
          }/>
          <input type="button" value="date" onClick={
              function () {
                  this.setState({date : (new Date()).toString()})
              }.bind(this)
          }/>
      </div>
    );
  }
}

export default App;
