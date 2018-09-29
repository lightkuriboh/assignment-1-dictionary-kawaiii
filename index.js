/** @format */

import {AppRegistry} from 'react-native';
import EngViet from "./components/EngViet/EngViet"
import App from './App';
import {name as appName} from './app.json';
import Menu from './components/Menu'
import DocumentTrans from './components/DocumentTrans/DocumentTrans'

AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerComponent(EngViet);
AppRegistry.registerComponent(Menu);
AppRegistry.registerComponent(DocumentTrans);