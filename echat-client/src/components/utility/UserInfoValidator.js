const UserInfoValidator = ({email, firstName, lastName, password}) => {
    return email != "" && firstName != "" && lastName != "" && password != "";
};

export default UserInfoValidator;
