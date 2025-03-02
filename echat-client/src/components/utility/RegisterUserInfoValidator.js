const RegisterUserInfoValidator = ({
  email,
  firstName,
  lastName,
  password,
}) => {
  function validateEmail() {
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email))
      throw new Error("enter valid email");
  }

  function validateNames() {
    if (String(firstName).length == 0 || String(lastName).length == 0)
      throw new Error("name is required");
  }

  function validatePassword() {
    if (String(password).length < 6)
      throw new Error("min password length should be 6");
  }

  return validateEmail() && validateNames() && validatePassword();
};

export default RegisterUserInfoValidator;
