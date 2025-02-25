import "../style/InputItem.css";

const evaluate = (placeholder) => {
  return placeholder === "password" ? "password" : "text";
};

const InputItem = ({ id, placeholder, value, onChange }) => {
  return (
    <input
      className="input-item"
      id={id}
      type={evaluate(placeholder)}
      placeholder={placeholder}
      value={value}
      onChange={onChange}
    />
  );
};

export default InputItem;
