import React from 'react';

class Todo extends React.Component {
    constructor(props){ //constructor(생성자)를 통해 todo 타이틀 등 매개변수를 넘긴다.
        super(props); //props 오브젝트 초기화
        this.state = {item: props.item}; //this.state를 item 변수와 props.item으로 초기화 
        //this.state.item을 통해 item 오브젝트에 접근 가능
        //state: 리액트가 관리하는 오브젝트. 리액트에서는 추후 변경할 수 있는 변수를 state 오브젝트에서 관리
    }
    render(){
        return (
            <div className = "Todo">
                <input 
                type = "checkbox" 
                id = {this.state.item.id}
                name = {this.state.item.id}
                checked = {this.state.item.done}
                />
                <label id = {this.state.item.id}>
                    {this.state.item.title}
                </label>
            </div>
        );
    }
}

export default Todo;