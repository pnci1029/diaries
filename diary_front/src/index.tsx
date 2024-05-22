import React from "react";
import './index.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Article from "./component/article/Article";
import {createRoot} from "react-dom/client";
import App from "./App";
import {Provider} from "react-redux";
import {store} from "./component/store";
import ArticleCreator from "./component/article/ArticleCreator";
import {WebsocketTest} from "./component/WebsocketTest";

const container = document.getElementById("root")!;
const root = createRoot(container);
root.render(
    <React.StrictMode>
        <Provider store={store}>
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<App/>} />
                    <Route path={"article"} element={<Article/>}>
                        <Route path={"article/create"} element={<ArticleCreator/>}/>
                    </Route>
                    <Route path={"websocket"} element={<WebsocketTest/>}/>
                </Routes>
            </BrowserRouter>
        </Provider>
    </React.StrictMode>
);

