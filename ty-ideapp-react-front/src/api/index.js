import axios from 'axios';

export const loginCall = (formData) => {
  return axios.post(process.env.REACT_APP_BACKEND+'/api/login', formData).then(
    response => response.data,
    error => {throw error}
  );
}

export const registrationCall = (user) => {
  return axios.post(process.env.REACT_APP_BACKEND+'/api/users/', user).then(
    response => response.data,
    error => {throw error}
  );
}

const apis = {
  loginCall: loginCall,
  registrationCall: registrationCall
}

export default apis;
