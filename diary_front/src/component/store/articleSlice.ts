import {createAsyncThunk, createSlice, PayloadAction} from "@reduxjs/toolkit";
import {ArticleApi} from "../../api/ArticleApi";

const initialState = {
    isArticleDataSaved: true,
    test: "TEST",
};

export const articleSlice = createSlice({
    name: "article",
    initialState,
    reducers: {
        setIsArticleDataSaved: (state, action: PayloadAction<boolean>) => {
            state.isArticleDataSaved = action.payload;
        },
    },
    extraReducers: (builder) => {
    },
});

export const { setIsArticleDataSaved } = articleSlice.actions;
export default articleSlice.reducer;

export const getArticlesAsync = createAsyncThunk("article/getArticles", () =>
    executePromise(ArticleApi.getArticles())
);

// export const executePromise = async <T>(f: () => Promise<AxiosResponse<T>>) => {
//     try {
//         return await f();
//     } catch (error: any) {
//         throw new Error(JSON.stringify(error?.response?.data));
//     }
// };

export const executePromise = async (promise: any) => {
    try {
        // 성공한 경우 결과 반환
        return await promise;
    } catch (error) {
        // 실패한 경우 에러 throw
        throw error;
    }
};

