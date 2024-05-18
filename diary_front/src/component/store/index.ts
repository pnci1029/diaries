import {Action, configureStore, ThunkAction} from '@reduxjs/toolkit';

import ArticleSlice from "./articleSlice";


export const store = configureStore({
    reducer: {
        article: ArticleSlice,
    },
});



export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<ReturnType, RootState, unknown, Action<string>>;
