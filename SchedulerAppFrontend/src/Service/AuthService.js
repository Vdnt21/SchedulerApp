import axios from "axios";

const SCHEDULER_BASE_URL = "http://localhost:8080";

export const registerAPICall = (registerObj) =>
  axios.post(SCHEDULER_BASE_URL + "/client", registerObj);

export const loginAPICall = (email, password) =>
  axios.post(SCHEDULER_BASE_URL + "/login", { email, password });
