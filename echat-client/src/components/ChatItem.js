import "../style/ChatItem.css";

const ChatItem = ({ sender, message }) => {
  return (
    <div className="chat-item">
      <div id="sender">{sender}</div>
      <div id="message">{message}</div>
    </div>
  );
};

export default ChatItem;
