import { baseApi } from "./base";


export async function registerUser( userData, options) {
    const res = await baseApi.post(`user/signup`, userData, options);
    return res.data;
}