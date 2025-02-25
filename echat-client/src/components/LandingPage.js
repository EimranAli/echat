import { React, useEffect, useState } from "react";
import SockJS from "sockjs-client";
import { over } from "stompjs";
import "../style/LandingPage.css";
import ChatItem from "./ChatItem";
import InputItem from "./InputItem";
import LabelItem from "./LabelItem";
import ButtonItem from "./ButtonItem";
import PopUpItem from "./PopUpItem";
import Sha512HashingAlgo from "./utility/Sha512HashingAlgo";
import axios from "axios";
import NewRoomCreator from "./utility/NewRoomCreator";
import UserInfoValidator from "./utility/UserInfoValidator";
import RoomInputValidator from "./utility/RoomInputValidator";
import SleepUtil from "./utility/SleepUtil";
import SetStateAndEraseAlterState from "./utility/SetStateAndEraseAlterState";

var stompClient = null;
const LandingPage = () => {
  const [text, setText] = useState("");
  const [currentUser, setCurrentUser] = useState({
    email: "",
    firstName: "",
    lastName: "",
  });

  const [loginRegisterToggle, setLoginRegisterToggle] = useState(true);
  const [displayRegistrationError, setDisplayRegistrationError] =
    useState(false);
  const [registerUserInfo, setRegisterUserInfo] = useState({
    email: "",
    firstName: "",
    lastName: "",
    password: "",
  });

  const [displayLoginError, setDisplayLoginError] = useState(false);
  const [loginUserInfo, setLoginUserInfo] = useState({
    email: "",
    password: "",
  });

  const [userLoggedInToggle, setUserLoggedInToggle] = useState(false);
  const [isUserConnected, setIsUserConnected] = useState(false);

  const enterRoomPage = () => {
    setUserLoggedInToggle(true);
    setIsUserConnected(false);
  };

  const [joinCreateRoomToggle, setJoinCreateRoomToggle] = useState(true);
  const [joinCreateInfo, setJoinCreateInfo] = useState({
    name: "",
    roomId: "",
  });

  const joinUser = () => {
    RoomInputValidator(joinCreateInfo);
    connectToSocket();
  };

  const updateRegisterUserInfo = (key, event) => {
    const { value } = event.target;

    switch (key) {
      case "email":
        setRegisterUserInfo({ ...registerUserInfo, email: value });
        break;
      case "firstName":
        setRegisterUserInfo({ ...registerUserInfo, firstName: value });
        break;
      case "lastName":
        setRegisterUserInfo({ ...registerUserInfo, lastName: value });
        break;
      case "password":
        setRegisterUserInfo({ ...registerUserInfo, password: value });
        break;
    }
  };

  const updateLoginUserInfo = (key, event) => {
    const { value } = event.target;

    switch (key) {
      case "email":
        setLoginUserInfo({ ...loginUserInfo, email: value });
        break;
      case "password":
        setLoginUserInfo({ ...loginUserInfo, password: value });
        break;
    }
  };

  const updateJoinCreateIno = (key, event) => {
    const { value } = event.target;
    switch (key) {
      case "joinName":
        setJoinCreateInfo({ ...joinCreateInfo, name: value });
        break;
      case "roomId":
        setJoinCreateInfo({ ...joinCreateInfo, roomId: value });
        break;
    }
  };

  const updateUserText = (event) => {
    const { value } = event.target;
    setText(value);
  };

  const loginPage = () => {
    return (
      <>
        <div className="login-container">
          {userLoggedInToggle && (
            <>
              <div className="join-create-room-container">
                <div className="join-room-class">
                  <LabelItem
                    id={
                      joinCreateRoomToggle ? "join-room-highlight" : "join-room"
                    }
                    label="Join Room"
                    onClick={() =>
                      setJoinCreateRoomToggle(!joinCreateRoomToggle)
                    }
                  />
                </div>
                {joinCreateRoomToggle && (
                  <div className="join-name-roomid-input">
                    <InputItem
                      id="join-name-input"
                      placeholder="name"
                      value={joinCreateInfo.name}
                      onChange={(event) =>
                        updateJoinCreateIno("joinName", event)
                      }
                    />
                    <InputItem
                      id="join-room-id-input"
                      placeholder="room id"
                      value={joinCreateInfo.roomId}
                      onChange={(event) => updateJoinCreateIno("roomId", event)}
                    />
                    <div className="join-room-button">
                      <ButtonItem
                        id="join-button"
                        label="Join!"
                        onClick={joinUser}
                      />
                    </div>
                  </div>
                )}
                <div className="create-room-class">
                  <LabelItem
                    id="create-room"
                    label="Create New Room"
                    onClick={() => NewRoomCreator(currentUser.email)}
                  />
                </div>
                <div id="room-id-container"></div>
              </div>
            </>
          )}
          {!userLoggedInToggle && (
            <>
              <div className="login-label-container">
                <LabelItem
                  id={
                    loginRegisterToggle
                      ? "login-label-highlight"
                      : "login-label"
                  }
                  label="Login"
                  onClick={() =>
                    SetStateAndEraseAlterState(
                      true,
                      setLoginRegisterToggle,
                      setDisplayRegistrationError,
                      setRegisterUserInfo
                    )
                  }
                />
                <LabelItem
                  id={
                    !loginRegisterToggle
                      ? "register-label-highlight"
                      : "register-label"
                  }
                  label="Register"
                  onClick={() =>
                    SetStateAndEraseAlterState(
                      false,
                      setLoginRegisterToggle,
                      setDisplayLoginError,
                      setLoginUserInfo
                    )
                  }
                />
              </div>
              <div className="input-items-container">
                {loginRegisterToggle && (
                  <>
                    <InputItem
                      id="email"
                      placeholder="email"
                      value={loginUserInfo.email}
                      onChange={(event) => updateLoginUserInfo("email", event)}
                    />
                    <InputItem
                      id="password"
                      placeholder="password"
                      value={loginUserInfo.password}
                      onChange={(event) =>
                        updateLoginUserInfo("password", event)
                      }
                    />
                  </>
                )}

                {!loginRegisterToggle && (
                  <>
                    <InputItem
                      id="email"
                      placeholder="email"
                      value={registerUserInfo.email}
                      onChange={(event) =>
                        updateRegisterUserInfo("email", event)
                      }
                    />
                    <InputItem
                      id="first-name"
                      placeholder="first name"
                      value={registerUserInfo.firstName}
                      onChange={(event) =>
                        updateRegisterUserInfo("firstName", event)
                      }
                    />
                    <InputItem
                      id="last-name"
                      placeholder="last name"
                      value={registerUserInfo.lastName}
                      onChange={(event) =>
                        updateRegisterUserInfo("lastName", event)
                      }
                    />
                    <InputItem
                      id="password"
                      placeholder="password"
                      value={registerUserInfo.password}
                      onChange={(event) =>
                        updateRegisterUserInfo("password", event)
                      }
                    />
                  </>
                )}
                <div>
                  <PopUpItem
                    id="popup-item"
                    label={
                      displayLoginError
                        ? "INCORRECT EMAIL OR PASSWORD!"
                        : displayRegistrationError
                          ? "REGISTRATION FAILED!"
                          : ""
                    }
                  />
                </div>
              </div>
              <div className="submit-button-container">
                <ButtonItem
                  id="submit-button"
                  label="Submit"
                  onClick={
                    loginRegisterToggle
                      ? proceedUserLogin
                      : proceedUserRegistration
                  }
                />
              </div>
            </>
          )}
        </div>
        <div className="logout" onClick={enterLoginPage}>
          logout
        </div>
      </>
    );
  };

  const proceedUserLogin = () => {
    if (loginUserInfo.email == "" || loginUserInfo.password == "")
      throw new Error("credentials can't be empty!");

    let hashedPassword = Sha512HashingAlgo(loginUserInfo.password);
    let url =
      "http://localhost:8080/echat/users/get-user?email=" +
      loginUserInfo.email +
      "&hashedPassword=" +
      hashedPassword;

    axios.get(url).then((r) => {
      if (r.status == "200") {
        setCurrentUser({
          email: r.data.email,
          firstName: r.data.name,
          lastName: r.data.lastName,
        });
        setUserLoggedInToggle(true);
      } else {
        displayErrorForSometime(displayLoginError, setDisplayLoginError);
      }
    });
  };

  const displayErrorForSometime = async (toggle, handler) => {
    handler(true);
    await SleepUtil(3000);
    handler(false);
  };

  const proceedUserRegistration = () => {
    if (!UserInfoValidator(registerUserInfo)) {
      throw new Error("user input not valid");
    }
    registerUser();
  };

  const connectToSocket = () => {
    let sock = new SockJS("http://localhost:8080/echat");
    stompClient = over(sock);
    stompClient.connect({}, onConnected, onError);
  };

  const registerUser = () => {
    let hashedPassword = Sha512HashingAlgo(registerUserInfo.password);
    let url = "http://localhost:8080/echat/users/register-user";
    let body = {
      email: registerUserInfo.email,
      firstName: registerUserInfo.firstName,
      lastName: registerUserInfo.lastName,
      hashedPassword: hashedPassword,
    };

    axios.post(url, body).then((r) => {
      if (r.data === "ACCEPTED") {
        alert("user registered");
      } else {
        displayErrorForSometime(
          displayRegistrationError,
          setDisplayRegistrationError
        );
      }
    });
  };

  const onConnected = () => {
    // todo clean members and chats when joining, make this work

    updateDBWithNewUserAndUIWithCurrentGroupChat();
    setIsUserConnected(true);
    stompClient.subscribe(
      "/chatrooms/" + joinCreateInfo.roomId,
      onPrivateMessageReceived
    );
  };

  const updateDBWithNewUserAndUIWithCurrentGroupChat = () => {
    let url = "http://localhost:8080/echat/chat-archive/update-member?roomId="+joinCreateInfo.roomId+"&member="+joinCreateInfo.name;

    try {
      axios
        .put(url)
        .then(() =>
          console.log("new member updated for room " + joinCreateInfo.roomId)
        )
        .then(() => {
          updateUIDataWithGroupChatDataFromDB();
        });
    } catch (error) {
      console.log(error);
      console.log("new member was not updated. check server");
    }
  };

  const updateUIDataWithGroupChatDataFromDB = () => {
    let url =
      "http://localhost:8080/echat/chat-archive/get-group-chat/" +
      joinCreateInfo.roomId;

    try {
      axios.get(url).then((r) => {
        r.data.members.forEach(member => {
          members.push(member);
        });
        r.data.chats.forEach(chat => {
          let uiChat = {
            sender:chat.sender, 
            message:chat.text
          }

          chats.push(uiChat);
        });

        updateUI();
        console.log("present data available for ui : " + members + " , " + chats);
      });
    } catch (error) {
      console.log(
        "something went wrong while getting group chats for roomId : " +
          joinCreateInfo.roomId
      );
    }
  };

  const updateUI = () => {
    setMembers([...members]);
    setChats([...chats]);
  }

  const onPrivateMessageReceived = (payload) => {
    var payloadData = JSON.parse(payload.body);
    if(payloadData.textMessage != null && payloadData.textMessage.trim() != "") {
      let chat = {
        sender: payloadData.sender,
        message: payloadData.textMessage,
      };

      if(!members.includes(payloadData.sender)){
        members.push(payloadData.sender);
        setMembers([...members]);
      }

      chats.push(chat);
      setChats([...chats]);

      updateChatInDB(chat);
    }
  };

  const updateChatInDB = (chat) => {
    let dbChat = {
      sender: chat.sender,
      text: chat.message
    }
    let url = "http://localhost:8080/echat/chat-archive/update-chat/" + joinCreateInfo.roomId;

    try {
      axios
        .put(url, dbChat)
        .then(() => console.log("new chat updated for room " + joinCreateInfo.roomId));
    } catch (error) {
      console.log(error);
      console.log("chat was not updated. check server");
    }
  };

  const onError = (e) => {
    console.log("error ocurred while connecting to websocket");
  };

  const enterLoginPage = () => {
    setIsUserConnected(false);
    setUserLoggedInToggle(false);
  };

  const sendMessage = () => {
    if (stompClient) {
      let message = {
        sender: joinCreateInfo.name,
        roomId: joinCreateInfo.roomId,
        command: "send",
        textMessage: text,
      };
      stompClient.send("/server/private-rooms", {}, JSON.stringify(message));
      // clear input
      setText("");
    }
  };

  const [members, setMembers] = useState([]);
  const [chats, setChats] = useState([]);

  const chatPage = () => {
    return (
      <>
        <div className="chatroom-container">
          <div className="user-container" id="membersList">
            {members.map((item) => {
              return <li id="member">{item}</li>;
            })}
          </div>
          <div className="chat-container">
            <div className="chat-list">
              {chats.map((item) => {
                return <ChatItem sender={item.sender} message={item.message} />;
              })}
            </div>
            <div className="chat-input-container">
              <input
                id="chat-input-text"
                placeholder="type here to send"
                value={text}
                onChange={(event) => updateUserText(event)}
              ></input>
              <button id="chat-input-send" onClick={sendMessage}>
                send
              </button>
            </div>
          </div>
        </div>
        <div className="logout" onClick={enterLoginPage}>
          logout
        </div>
        <div className="join-other-room" onClick={enterRoomPage}>
          Join Other Room
        </div>
      </>
    );
  };

  return isUserConnected ? chatPage() : loginPage();
};

export default LandingPage;
