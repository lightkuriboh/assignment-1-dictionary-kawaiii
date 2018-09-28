/** @format */

import {AppRegistry} from 'react-native';
import EngViet from "./components/EngViet/EngViet"
import App from './App';
import {name as appName} from './app.json';

AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerComponent(EngViet);
