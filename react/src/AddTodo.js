import React from "react";
import { TextField, Paper, Button, Grid } from "@material-ui/core";

class AddTodo extends React.Component {
  constructor(props) {
    super(props);
    this.state = { item: { title: "" } };//input필드에 입력하는 정보를 컴포넌트 내부에 임시 저장하기 위함
    this.add = props.add;//Add.js에서 작성한 add() 함수를 props로 넘겨받는다. props의 함수를 this.add에 연결 
  }

  onInputChange = (e) => {//Event e를 매개변수로 받는다. 키보드 입력시마다 Event e 발생 (onChange) -> onChange에 연결된 onInputChange 실행 
    const thisItem = this.state.item;
    thisItem.title = e.target.value;//target.value: 현재 화면의 InputField에 입력된 string 
    this.setState({ item: thisItem });//업데이트된 thisItem을 반영
    console.log(thisItem);
  };

  onButtonClick = () => {
    this.add(this.state.item);
    this.setState({ item: { title: "" } });
  };

  enterKeyEventHandler = (e) => {
    if (e.key === "Enter") {
      this.onButtonClick();
    }
  };

  render() {
    return (
      <Paper style={{ margin: 16, padding: 16 }}>
        <Grid container>
          <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
            <TextField
              placeholder="Add Todo here"
              fullWidth
              onChange={this.onInputChange}
              value={this.state.item.title}
              onKeyPress={this.enterKeyEventHandler}
            />
          </Grid>
          <Grid xs={1} md={1} item>
            <Button
              fullWidth
              color="secondary"
              variant="outlined"
              onClick={this.onButtonClick}
            >
              +
            </Button>
          </Grid>
        </Grid>
      </Paper>
    );
  }
}

export default AddTodo;