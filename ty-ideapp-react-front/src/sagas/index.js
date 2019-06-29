import { call, put, takeLatest } from 'redux-saga/effects';
import Api from '../api';

import { LOGIN_REQUESTED, REGISTRATION_REQUESTED } from '../actions/types';
import { fetchLoggedUser, loginError, registrationError } from '../actions/userActions';
import { resetApp } from '../actions/rootActions';

function* login(action) {
   try {
      const user = yield call(Api.loginCall, action.formData);
      yield put(resetApp());
      yield put(fetchLoggedUser(user));
   } catch (e) {
     yield put(loginError(e.message));
   }
}

function* register(action) {
  try {
    let formData = new FormData();
    formData.set('username', action.user.username);
    formData.set('password', action.user.password);

    let user = yield call(Api.registrationCall, action.user);
    user = yield call(Api.loginCall, formData);
    yield put(resetApp());
    yield put(fetchLoggedUser(user));
  } catch (e) {
    if(e.response && e.response.data) {
      yield put(registrationError(e.response.data));
    } else {
      yield put(registrationError(e.message));
    }
  }
}

function* rootSaga() {
  yield takeLatest(LOGIN_REQUESTED, login);
  yield takeLatest(REGISTRATION_REQUESTED, register);
}

export default rootSaga;
