//index.html과 가장 처음으로 실행되는 자바스크립트 코드 
import React from 'react'; //리액트 사용을 위해
import ReactDOM from 'react-dom'; //리액트 DOM의 사용을 위해
import './index.css'; // css import
import App from './App'; //App 컴포넌트를 import
import reportWebVitals from './reportWebVitals';

//<컴포넌트이름/>을 이용해 컴포넌트를 사용한다.
ReactDOM.render( //ReactDOM이 내부 컴포넌트들을 'root' element 내 렌더함
  <React.StrictMode> 
    <App /> 
  </React.StrictMode>,
  document.getElementById('root')
); //첫번째로 App을, 두번째로 root을 매개변수로 받는다. 이는 root element에 App을 추가하라는 뜻이다. 
//React로 만든 모든 컴포넌트는 이 root 하위에 추가된다!

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
