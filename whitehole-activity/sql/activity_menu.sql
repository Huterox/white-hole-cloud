-- 菜单SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    VALUES ('1', '这个是我们创建的活动的一些内容，这个是在运营阶段由管理人员创建的，当运营确定好活动之后，
我们这边就会进行创建新的活动，之后在技术层面实现，注意：activity 和 admin 都是管理人使用的模块。

注意的是，我们的这个记录的是我们这个由管理人员创建的玩意来的，实际上我们还有系统自动的一些活动，这些活动是系统级别的。



', 'whiteholeactivity/activity', NULL, '1', 'config', '6');

-- 按钮父菜单ID
set @parentId = @@identity;

-- 菜单对应按钮SQL
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '查看', null, 'whiteholeactivity:activity:list,whiteholeactivity:activity:info', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '新增', null, 'whiteholeactivity:activity:save', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '修改', null, 'whiteholeactivity:activity:update', '2', null, '6';
INSERT INTO `sys_menu` (`parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
    SELECT @parentId, '删除', null, 'whiteholeactivity:activity:delete', '2', null, '6';
