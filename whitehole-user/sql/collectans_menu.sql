-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    VALUES ('1', '这个就是用户收藏的问题的回答了，我们会直接定位到问题的具体的回答页面。
而，对于用户收藏的问题的话，我们是直接跳转到用户所收藏的问题页面。

由于这些玩意都是收藏的部分，所以的话，我们需要存储一个状态，我们默认状态都是1 也就是正常的，当访问具体的页面
的时候，做一个判断，如果说当前是status，他是异常的例如被删除或者下架了，那么再设置这个玩意为真实的status，用户可以选择删除这个玩意，玩意这个玩意又
正常了，失误删除就可惜了。


', 'user/collectans', NULL, '1', 'config', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '查看', null, 'user:collectans:list,user:collectans:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '新增', null, 'user:collectans:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '修改', null, 'user:collectans:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '删除', null, 'user:collectans:delete', '2', null, '6';
