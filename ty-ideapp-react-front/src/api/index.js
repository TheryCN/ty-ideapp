import axios from 'axios';

export const loginCall = (formData) => {
  return axios.post(process.env.REACT_APP_BACKEND+'/api/login', formData).then(
    response => response.data,
    error => {throw error}
  );
}

export const registrationCall = (user) => {
  return axios.post(process.env.REACT_APP_BACKEND+'/api/user/', user).then(
    response => response.data,
    error => {throw error}
  );
}

export default {
  loginCall: loginCall,
  registrationCall: registrationCall
}
