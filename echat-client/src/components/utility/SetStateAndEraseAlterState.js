const SetStateAndEraseAlterState = (stateToSet, stateSet, alterErrorSet, alterInfoEraseSet) => {
        stateSet(stateToSet);
        alterErrorSet(false);
        alterInfoEraseSet({});
}

export default SetStateAndEraseAlterState;
