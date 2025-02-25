import "../style/RoomItem.css";
import { useState } from "react";
import SleepUtil from "./utility/SleepUtil";

const RoomItem = ({ props }) => {
  const signalUser = async () => {
    setCopyToggle(false);
    await SleepUtil(400);
    setCopyToggle(true);
  };

  const [copyToggle, setCopyToggle] = useState(false);
  const [clickedToggle, setClickedToggle] = useState(false);
  return (
    <div className="room-item-container">
      <div
        className="room-item"
        onClick={() => {
          setClickedToggle(true);
          navigator.clipboard.writeText(props.label);
          signalUser();
        }}
      >
        {props.label}
      </div>
      {clickedToggle && (
        <div className="copy-signal">
          {copyToggle ? "copied!" : "copying...."}
        </div>
      )}
    </div>
  );
};

export default RoomItem;
