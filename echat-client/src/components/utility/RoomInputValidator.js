const RoomInputValidator = ({name, roomId}) => {
    if( name == "" || roomId == "")
        throw new Error("enter proper inputs to join a room");
}

export default RoomInputValidator;