import {HttpClient} from "../component/hooks/HttpClient";

export class MainApi extends HttpClient{
    private static classInstance?: MainApi;

    public static api = MainApi.getInstance().instance;
    public static urlPrefix = "/api";

    private constructor() {
        console.log(process.env.REACT_APP_BASE_URL as string)

        super(process.env.REACT_APP_BASE_URL as string);
    }

    public static getInstance() {
        if (!this.classInstance) {
            this.classInstance = new MainApi();
        }

        return this.classInstance;
    }

    public setToken(token: string) {
        this.instance.defaults.headers.common["Authorization"] = `Bearer ${token}`;
    }


}