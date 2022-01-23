import React from "react";
import Todo from "./Todo";
import AddTodo from "./AddTodo.js"; //왜 얘는 js?
import { Paper, List, Container } from "@material-ui/core";
import "./App.css";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      items: [],
    };
  }

  //함수 추가
  add = (item) => {
    const thisItems = this.state.items;
    item.id = "ID-" + thisItems.length; // key를 위한 id추가. length를 id로 하는 이유?
    item.done = false; // done 초기화
    thisItems.push(item); // 배열에 아이템 추가
    this.setState({ items: thisItems }); // 반영. 업데이트는 반드시 this.setState로 해야 한다.
    console.log("items : ", this.state.items);
  };

  delete = (item) => {
    const thisItems = this.state.items;
    console.log("Before Update Items : ", this.state.items);
    const newItems = thisItems.filter((e) => e.id !== item.id); // 해당 id 걸러내기
    this.setState({ items: newItems }, () => {
      // 디버깅 콜백
      console.log("Update Items : ", this.state.items);
    });
  };

  //delete={this.delete}는 아이템 당 할당되어서. 
  render() {
    var todoItems = this.state.items.length > 0 && (
      <Paper style={{ margin: 16 }}>
        <List>
          {this.state.items.map((item, idx) => (
            <Todo item={item} key={item.id} delete={this.delete} />
          ))}
        </List>
      </Paper>
    );

    //함수 연결 (<AddTodo add = {this.add}/>)
    return (
      <div className="App">
        <Container maxWidth="md">
          <AddTodo add={this.add} />
          <div className="TodoList">{todoItems}</div>
        </Container>
      </div>
    );
  }
}

export default App;