import "../style/PopUpItem.css";

const PopUpItem = ({ id, label }) => {
  return (
    <div className="popup-item-class" id={id}>
      {label}
    </div>
  );
};

export default PopUpItem;
