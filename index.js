// main index.js

import {NativeModules, DeviceEventEmitter, NativeEventEmitter} from 'react-native';
const {MuteSwitch} = NativeModules;
const emitter = new NativeEventEmitter(NativeModules.MuteSwitch);

const subscribe = (callback) => {
  const listener = emitter.addListener('MuteSwitch_change', callback);
  return listener.remove;
}

export {subscribe};
export default MuteSwitch;
