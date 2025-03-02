import RegisterUserInfoValidator from "./RegisterUserInfoValidator";

const LoginUserInfoValidator = ({ email, password }) => {
  RegisterUserInfoValidator({
    email: email,
    firstName: "dummy",
    lastName: "dummy",
    password: password,
  });
};

export default LoginUserInfoValidator;
