// import {Action, combineReducers, configureStore, ThunkAction} from "@reduxjs/toolkit";
import {Action, configureStore, ThunkAction, ThunkDispatch} from '@reduxjs/toolkit';

import ArticleSlice, {articleSlice} from "./articleSlice";

// const rootReducer = combineReducers({
//     potato: 'potatoReducer',
//     tomato: 'tomatoReducer',
// });

// export default rootReducer;

export const store = configureStore({
    reducer: {
        article: ArticleSlice,
    },
    // middleware: [],
});



export type AppDispatch = typeof store.dispatch;
export type RootState = ReturnType<typeof store.getState>;
export type AppThunk<ReturnType = void> = ThunkAction<ReturnType, RootState, unknown, Action<string>>;
