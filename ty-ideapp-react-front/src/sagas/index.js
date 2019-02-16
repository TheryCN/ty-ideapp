import { call, put, takeLatest } from 'redux-saga/effects';
import Api from '../api';

import { LOGIN_REQUESTED } from '../actions/types';
import { fetchLoggedUser, loginError } from '../actions/userActions';
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

function* rootSaga() {
  yield takeLatest(LOGIN_REQUESTED, login);
}

export default rootSaga;
