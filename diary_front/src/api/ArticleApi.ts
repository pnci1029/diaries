import {MainApi} from "./MainApi";

export class ArticleApi {
    static baseUrl = `${MainApi.urlPrefix}/article`;

    static getArticles = () => () => MainApi.api.get(`${ArticleApi.baseUrl}/`);
}