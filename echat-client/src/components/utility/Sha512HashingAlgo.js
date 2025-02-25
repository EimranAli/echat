import { sha512 } from "js-sha512";

const Sha512HashingAlgo = (password) => {
  var hash = sha512.create();
  hash.update(password);
  return hash.hex();
};

export default Sha512HashingAlgo;
