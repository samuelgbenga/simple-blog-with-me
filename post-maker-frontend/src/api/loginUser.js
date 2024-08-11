import { baseApi } from "./base";


export async function loginUser( loginData, options) {
    const res = await baseApi.post(`user/login`, loginData, options);
    return res.data;
}