import "../style/LabelItem.css";

const LabelItem = ({ id, label, onClick }) => {
  return (
    <div className="label-item" id={id} onClick={onClick}>
      {label}
    </div>
  );
};

export default LabelItem;
