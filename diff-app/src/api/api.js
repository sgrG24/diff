import axios from "axios";

const baseUrl = "http://localhost:8081";

export const generateDiff = (originalText, modifiedText) => {
  return axios({
    method: "post",
    url: baseUrl + "/diff",
    headers: {},
    data: {
      original: originalText,
      modified: modifiedText,
    },
  });
};
