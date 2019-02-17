artDialog.alert = function (content, callback) {
    artDialog.through({
        id: "success",
        lock: true,
        icon: "warning",
        title: "消息提示",
		width: 290,
        height: 130,
        content: content,
		okVal:'确 定',
        ok: true,
		cancel:callback
    });
};

artDialog.success = function (content, callback) {
    artDialog.through({
        id: "success",
        lock: true,
        icon: "succeed",
        title:"成功提示",
        content: content,
        ok: callback
    });
};
artDialog.error = function (content, callback) {
    artDialog.through({
        id: "error",
        lock: true,
        icon: "error",
        title: "错误提示",
        content: content,
		width: 290,
        height: 130,
        okVal:'确 定',
        ok: true,
		cancel:callback
    });
};
artDialog.confirm = function (content, callback1,callback2) {
    artDialog.through({
        id: "confirm",
        lock: true,
		width: 290,
        height: 130,
        icon: "question",
        title: "操作确认",
        content: content,
		okVal:'确 定',
        ok: callback1,
		cancelVal:'取 消',
		cancel:callback2
    });
};