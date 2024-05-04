import axios, {AxiosInstance} from "axios";

export class MainApi {
    private static classInstance?: MainApi;
    public readonly instance: AxiosInstance = axios.create();

    public static api = MainApi.getInstance().instance;
    public static urlPrefix = "/api";
    public static processUrl = process.env.REACT_APP_BASE_URL;

    private constructor() {
        this.instance = axios.create({
            baseURL: MainApi.processUrl,
        });
    }

    public static getInstance() {
        if (!this.classInstance) {
            this.classInstance = new MainApi();
        }

        return this.classInstance;
    }


}