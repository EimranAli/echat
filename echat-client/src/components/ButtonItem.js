import "../style/ButtonItem.css";

const ButtonItem = ({ id, label, onClick }) => {
  return (
    <button className="button-item" id={id} onClick={onClick}>
      {label}
    </button>
  );
};

export default ButtonItem;
