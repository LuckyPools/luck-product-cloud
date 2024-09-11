import request from "@/utils/request";

/**
 * 获取当前登录的用户信息、菜单、权限、角色
 */
export async function getUserInfo() {
    return await request.get('/sys/user/getUserLoginInfo');
}


/**
 * 分页查询用户
 * @param params 查询条件
 */
export async function pageUsers(params) {
    return await request.post('/sys/user/page', params);
}


export async function fetchGetUserList() {
    return {
        "data": {
            "records": [
                {
                    "id": 1,
                    "createBy": "Jeffrey Gonzalez",
                    "createTime": "2016-04-07 05:36:02",
                    "updateBy": "Shirley Robinson",
                    "updateTime": "1988-04-21 07:38:54",
                    "status": "2",
                    "userName": "IKw4bz4VKg0J4M",
                    "userGender": "1",
                    "nickName": "Deborah Hall",
                    "userPhone": "13654765490",
                    "userEmail": "m.nantxgy@wtw.中国互联.网络",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 2,
                    "createBy": "Mary Thomas",
                    "createTime": "1973-06-26 08:36:35",
                    "updateBy": "Robert Thomas",
                    "updateTime": "1993-04-26 05:09:19",
                    "status": "1",
                    "userName": "QXXbfI",
                    "userGender": "2",
                    "nickName": "Barbara Taylor",
                    "userPhone": "13172858794",
                    "userEmail": "x.fyic@exctgxvvb.us",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 3,
                    "createBy": "Matthew Robinson",
                    "createTime": "1987-01-19 19:52:30",
                    "updateBy": "Brian Martin",
                    "updateTime": "1990-11-12 08:31:06",
                    "status": "2",
                    "userName": "Ne4LsEg4R",
                    "userGender": "1",
                    "nickName": "Kevin White",
                    "userPhone": "13561024648",
                    "userEmail": "o.wloupramz@drlbqdyrt.gy",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 4,
                    "createBy": "Robert Rodriguez",
                    "createTime": "1989-05-11 02:55:37",
                    "updateBy": "Laura Brown",
                    "updateTime": "1975-08-21 11:33:16",
                    "status": "1",
                    "userName": "WfYr3Sw",
                    "userGender": "1",
                    "nickName": "Jason Garcia",
                    "userPhone": "15357305202",
                    "userEmail": "t.kwcpscmlf@isick.sj",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 5,
                    "createBy": "Cynthia Lee",
                    "createTime": "2001-10-23 06:32:03",
                    "updateBy": "Christopher Williams",
                    "updateTime": "1994-04-23 08:33:02",
                    "status": "2",
                    "userName": "rwWZEeD",
                    "userGender": "2",
                    "nickName": "Helen Taylor",
                    "userPhone": "15557883977",
                    "userEmail": "r.lzwerp@wxhodmpg.cl",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 6,
                    "createBy": "Mary Lee",
                    "createTime": "2003-06-29 16:21:50",
                    "updateBy": "Edward Jones",
                    "updateTime": "2022-04-22 15:22:32",
                    "status": "2",
                    "userName": "gwSHB",
                    "userGender": "1",
                    "nickName": "Ruth Lee",
                    "userPhone": "13337564749",
                    "userEmail": "y.bvbkkrl@ngwlhcrd.museum",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 7,
                    "createBy": "Brian Wilson",
                    "createTime": "2018-04-03 23:57:10",
                    "updateBy": "Jennifer Davis",
                    "updateTime": "1973-04-19 15:48:31",
                    "status": "1",
                    "userName": "DEH30",
                    "userGender": "2",
                    "nickName": "Anthony Walker",
                    "userPhone": "16678162323",
                    "userEmail": "j.hrkpk@snfsfnqhd.ye",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 8,
                    "createBy": "David Hall",
                    "createTime": "1996-05-02 22:17:20",
                    "updateBy": "Steven Garcia",
                    "updateTime": "2007-01-21 11:49:12",
                    "status": "2",
                    "userName": "b0NPGrxKq7",
                    "userGender": "2",
                    "nickName": "Cynthia Martin",
                    "userPhone": "13307714777",
                    "userEmail": "o.hefuhkmuh@ufsjauluo.cl",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 9,
                    "createBy": "Mark Perez",
                    "createTime": "2006-04-12 12:16:01",
                    "updateBy": "Brian Wilson",
                    "updateTime": "1971-04-30 03:49:33",
                    "status": "1",
                    "userName": "xQRIPn8L",
                    "userGender": "2",
                    "nickName": "Jose Gonzalez",
                    "userPhone": "17786846169",
                    "userEmail": "y.jwlpr@bosuz.lc",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                },
                {
                    "id": 10,
                    "createBy": "Brian Martin",
                    "createTime": "1989-03-04 17:38:19",
                    "updateBy": "Amy Perez",
                    "updateTime": "1970-12-13 04:56:05",
                    "status": "2",
                    "userName": "DYq81",
                    "userGender": "1",
                    "nickName": "Jeffrey Clark",
                    "userPhone": "15278754537",
                    "userEmail": "s.sbtckmdh@nnskobil.中国",
                    "userRoles": [
                        "R_QJQB_ECSMK",
                        "R_IQJL_VCTWY",
                        "R_WQKC_CRDET"
                    ]
                }
            ],
            "current": 1,
            "size": 10,
            "total": 200
        },
        "code": "0000",
        "msg": "请求成功"
    };
}
