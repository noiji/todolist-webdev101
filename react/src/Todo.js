import React from "react";
import {
  ListItem,
  ListItemText,
  InputBase,
  Checkbox,
  ListItemSecondaryAction,
  IconButton,
} from "@material-ui/core";

import DeleteOutlined from "@material-ui/icons/DeleteOutlined";

class Todo extends React.Component {
  constructor(props) {//constructor(생성자)를 통해 todo 타이틀 등 매개변수를 넘긴다.
    super(props);//props 오브젝트 초기화
    this.state = { item: props.item, readOnly: true }; //this.state를 item 변수와 props.item으로 초기화 
    //readOnly: true인 경우 아이템 수정 가능 -> Enter나 Return키 누를 때 / false인 경우 아이템 수정 가능 -> title을 클릭할 때 readOnly는 false가 되어 수정할 수 있게 된다.  
    //this.state.item을 통해 item 오브젝트에 접근 가능
    //state: 리액트가 관리하는 오브젝트. 리액트에서는 추후 변경할 수 있는 변수를 state 오브젝트에서 관리
    this.delete = props.delete; //App.js에서 작성한 delete()함수 연결
  }

  deleteEventHandler = () => { //App.js에서 작성한 delete()함수 추가
    this.delete(this.state.item);
  };

  offReadOnlyMode = () => {
    console.log("Event!", this.state.readOnly);
    this.setState({ readOnly: false }, () => { 
      console.log("ReadOnly? ", this.state.readOnly);
    });
  };

  enterKeyEventHandler = (e) => {//readOnly: true인 경우 아이템 수정 가능 -> Enter나 Return키 누를 때
    if (e.key === "Enter") {
      this.setState({ readOnly: true });
    }
  };

  //수정(1) 항목 이름을 바꿀 때
  editEventHandler = (e) => { 
    const thisItem = this.state.item;
    thisItem.title = e.target.value;
    this.setState({ item: thisItem });
  };

  //수정(2) 항목 진행 여부 변경할 때
  checkboxEventHandler = (e) => {
    const thisItem = this.state.item;
    thisItem.done = !thisItem.done;
    this.setState({ item: thisItem });
  };

  render() {
    const item = this.state.item;
    return (
      <ListItem>
        <Checkbox checked={item.done} onChange={this.checkboxEventHandler}  //수정(2) 항목 진행 여부 변경시
        /> 
        <ListItemText>
          <InputBase
            inputProps={{
              "aria-label": "naked",
              readOnly: this.state.readOnly,
            }}
            type="text"
            id={item.id}
            name={item.id}
            value={item.title}
            fullWidth={true}
            onClick={this.offReadOnlyMode} //타이틀 클릭 시 수정 가능 mode로 변경된다. 
            onChange={this.editEventHandler} //수정(1) 항목 이름 수정
            onKeyPress={this.enterKeyEventHandler}
          />
        </ListItemText>

        <ListItemSecondaryAction>
          <IconButton
            aria-label="Delete Todo" //삭제 아이콘 추가
            onClick={this.deleteEventHandler} //deleteEventHandler 연결 
          >
            <DeleteOutlined />
          </IconButton>
        </ListItemSecondaryAction>
      </ListItem>
    );
  }
}

export default Todo;
