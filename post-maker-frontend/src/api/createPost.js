import { baseApi } from "./base";


export async function createPost(username, postData, options) {
    const res = await baseApi.post(`user/${username}/newPost`, postData, options);
    return res.data;
}