import { baseApi } from "./base";

// get comment by id
export async function getPosts(username, options) {
    const res = await baseApi.get(`user/${username}/get-all-post`, options);
    return res.data;
  }