//기본으로 생성된 리액트 컴포넌트
import React from 'react';
import Todo from './Todo';
import logo from './logo.svg';
import './App.css';

class App extends React.Component {
  constructor(props){
    super(props);
    this.state = {
      items: [ //item 여러개인 경우 []안에 넣는다
        {id:0, title: "Hello World1", done: true}, //item 초기화. this.state.item을 통해 item 오브젝트에 접근 가능!!!!!!!!!!
        {id:1, title: "Hello World2", done: false}, //item 초기화. this.state.item을 통해 item 오브젝트에 접근 가능!!!!!!!!!!
      ],
    };
  }
  render() {
    //Todo를 늘어놓는 대신 return 앞에 자바스크립트가 제공하는 map 함수를 이용해 배열 반복하여 <Todo ../> 컴포넌트 생성
    
    var todoItems = this.state.items.map((item, idx) => (
        <Todo item = {item} key = {item.id} />
    ));
    
    return <div className="App"> {todoItems} </div>
      //아래 대신 위와 같이 한다.
      // <div className="App">
      //   <Todo item = {this.state.item1}/>
      //   <Todo item = {this.state.item2}/>
      // </div>
  }
}

export default App; //App이라는 컴포넌트를 다른 컴포넌트에서 사용 가능
