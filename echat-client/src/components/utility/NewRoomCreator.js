import { v4 as uuidv4 } from "uuid";
import RoomItem from "../RoomItem";
import { createRoot } from "react-dom";
import axios from "axios";

const NewRoomCreator = (roomCreator) => {
  let roomId = uuidv4();
  const parentDiv = document.getElementById("room-id-container");
  const childDiv = document.createElement("div");
  childDiv.className = "room-id-holder";
  parentDiv.appendChild(childDiv);

  const registerRoom = () => {
    let url =
      "http://localhost:8080/echat/chat-archive/register-group-chat?roomId=" +
      roomId +
      "&roomCreator=" +
      roomCreator;

    try {
      axios.post(url).then(() => {
        console.log(roomId + " registered successfully in database");
      });
    } catch (error) {
      throw new Error("something went wrong at server side");
    }
  };
  registerRoom(roomId, roomCreator);

  createRoot(childDiv).render(<RoomItem props={{ label: roomId }} />);
};

export default NewRoomCreator;
