package org.ovirt.engine.core.common;

import java.util.HashMap;
import java.util.Map;

public enum AuditLogType {
    UNASSIGNED(0),
    // -- VDC Log types --
    VDC_START(1),

    VDC_STOP(2),
    // -- VDS Log types --
    VDS_FAILURE(12), // When VDS changes status to up -> down or in VDC
    // initialization the VDS is down
    VDS_DETECTED(13), // When VDC initializes and detects a running VDS
    VDS_RECOVER(14), // When VDS changes status down->up
    VDS_MAINTENANCE(15), // When VDS is transferred to maintenance mode
    VDS_ACTIVATE(16), // When VDS is reactivated
    VDS_MAINTENANCE_FAILED(17, AuditLogTimeInterval.MINUTE.getValue()), // When VDS is transferred to maintenance mode
    VDS_ACTIVATE_FAILED(18, AuditLogTimeInterval.MINUTE.getValue()), // When VDS is reactivated
    VDS_RECOVER_FAILED(19, AuditLogTimeInterval.MINUTE.getValue()), // When VDS changes status down->up
    VDS_STATUS_CHANGE_FAILED_DUE_TO_STOP_SPM_FAILURE(20, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_SLOW_STORAGE_RESPONSE_TIME(123, AuditLogTimeInterval.MINUTE.getValue() * 5), // ?
    VDS_ALREADY_IN_REQUESTED_STATUS(493),
    VDS_MANUAL_FENCE_STATUS(494),
    VDS_MANUAL_FENCE_STATUS_FAILED(495),
    VDS_MANUAL_FENCE_FAILED_CALL_FENCE_SPM(530),
    VDS_LOW_MEM(531, AuditLogTimeInterval.MINUTE.getValue() * 30),
    VDS_HIGH_MEM_USE(532, AuditLogTimeInterval.MINUTE.getValue() * 30),
    VDS_HIGH_NETWORK_USE(533, AuditLogTimeInterval.MINUTE.getValue() * 30),
    VDS_HIGH_CPU_USE(534, AuditLogTimeInterval.MINUTE.getValue() * 30),
    VDS_HIGH_SWAP_USE(535, AuditLogTimeInterval.MINUTE.getValue() * 30),
    VDS_LOW_SWAP(536, AuditLogTimeInterval.MINUTE.getValue() * 30),
    VDS_FENCE_STATUS(496),
    VDS_FENCE_STATUS_FAILED(497),
    VDS_APPROVE(498),
    VDS_APPROVE_FAILED(499),
    VDS_FAILED_TO_RUN_VMS(500, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_INSTALL(504),
    VDS_INSTALL_FAILED(505),
    VDS_INITIATED_RUN_VM(506),
    VDS_INITIATED_RUN_VM_AS_STATELESS(537),
    VDS_INITIATED_RUN_VM_FAILED(507),
    VDS_INITIATED_RUN_AS_STATELESS_VM_NOT_YET_RUNNING(525),
    // VDS_INITIATED_RUN_VM_CHECKOUT_FAILED = 508,
    VDS_INSTALL_IN_PROGRESS(509),
    VDS_INSTALL_IN_PROGRESS_WARNING(510),
    VDS_INSTALL_IN_PROGRESS_ERROR(511),
    VDS_RECOVER_FAILED_VMS_UNKNOWN(513),
    VDS_INITIALIZING(514, AuditLogTimeInterval.SECOND.getValue() * 30),
    VDS_CPU_LOWER_THAN_CLUSTER(515),
    VDS_CPU_RETRIEVE_FAILED(516),
    VDS_FAILED_TO_GET_HOST_HARDWARE_INFO(517),
    @Deprecated
    VDS_STORAGE_CONNECTION_FAILED_BUT_LAST_VDS(533),
    VDS_STORAGES_CONNECTION_FAILED(535),
    VDS_STORAGE_VDS_STATS_FAILED(534),
    VDS_SET_NONOPERATIONAL(517, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_SET_NONOPERATIONAL_FAILED(518, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_SET_NONOPERATIONAL_NETWORK(519, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_SET_NONOPERATIONAL_IFACE_DOWN(603, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_SET_NONOPERATIONAL_DOMAIN(522, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_SET_NONOPERATIONAL_DOMAIN_FAILED(523, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_DOMAIN_DELAY_INTERVAL(524, AuditLogTimeInterval.HOUR.getValue()),
    VDS_LOW_DISK_SPACE(23, AuditLogTimeInterval.HOUR.getValue() * 12),
    VDS_LOW_DISK_SPACE_ERROR(24, AuditLogTimeInterval.MINUTE.getValue() * 15),
    USER_VDS_MAINTENANCE(600), // When VDS is transferred to
    CPU_FLAGS_NX_IS_MISSING(601),
    // maintenance mode
    USER_VDS_MAINTENANCE_MIGRATION_FAILED(602),

    SYSTEM_VDS_RESTART(121, AuditLogTimeInterval.MINUTE.getValue()),
    SYSTEM_FAILED_VDS_RESTART(122, AuditLogTimeInterval.MINUTE.getValue()),

    // Host time drift Alert
    VDS_TIME_DRIFT_ALERT(604, AuditLogTimeInterval.MINUTE.getValue() * 60),

    // Proxy host selection
    PROXY_HOST_SELECTION(605),

    HOST_REFRESHED_CAPABILITIES(606),
    HOST_REFRESH_CAPABILITIES_FAILED(607),

    // Disk alignment audit logs
    DISK_ALIGNMENT_SCAN_START(700),
    DISK_ALIGNMENT_SCAN_FAILURE(701),
    DISK_ALIGNMENT_SCAN_SUCCESS(702),

    // -- IRS Log types --
    IRS_FAILURE(22, AuditLogTimeInterval.HOUR.getValue() * 12),
    IRS_DISK_SPACE_LOW(26, AuditLogTimeInterval.HOUR.getValue() * 12),
    IRS_DISK_SPACE_LOW_ERROR(201, AuditLogTimeInterval.MINUTE.getValue() * 15),
    IRS_HOSTED_ON_VDS(204),
    // -- USER Log types --
    USER_VDC_LOGIN(30, AuditLogTimeInterval.SECOND.getValue() * 5), // user logged in to VDC
    USER_VDC_LOGIN_FAILED(114), // user logged in to VDC
    USER_VDC_LOGOUT(31), // User logged out from VDC
    USER_VDC_LOGOUT_FAILED(815), // User logged out failed

    USER_INITIATED_RUN_VM(150),
    USER_INITIATED_RUN_VM_AND_PAUSE(156),
    USER_STARTED_VM(153),
    USER_INITIATED_RUN_VM_FAILED(151),
    USER_RUN_VM(32), // User issued runVm command
    USER_RUN_VM_AS_STATELESS(538),  // User issued runVm command in stateless mode
    USER_FAILED_RUN_VM(54), // User issued runVm command
    USER_RUN_VM_AS_STATELESS_FINISHED_FAILURE(70),
    USER_RUN_VM_AS_STATELESS_WITH_DISKS_NOT_ALLOWING_SNAPSHOT(171),
    USER_RUN_VM_FAILURE_STATELESS_SNAPSHOT_LEFT(1001),
    USER_RUN_VM_ON_NON_DEFAULT_VDS(152),

    USER_REBOOT_VM(157),
    USER_FAILED_REBOOT_VM(158),

    USER_STOP_VM(33), // User issued stopVm command
    USER_STOP_SUSPENDED_VM(111),
    USER_STOP_SUSPENDED_VM_FAILED(112),
    USER_FAILED_STOP_VM(56),
    USER_ADD_VM(34),
    USER_ADD_VM_STARTED(37),
    USER_ADD_VM_FINISHED_SUCCESS(53),
    USER_ADD_VM_FINISHED_FAILURE(60),
    USER_FAILED_ADD_VM(57),
    USER_UPDATE_VM(35),
    USER_FAILED_UPDATE_VM(58),
    USER_UPDATE_VM_CLUSTER_DEFAULT_HOST_CLEARED(250),
    USER_REMOVE_VM_FINISHED(113),
    USER_REMOVE_VM_FINISHED_WITH_ILLEGAL_DISKS(172),
    USER_ADD(149),
    USER_FAILED_REMOVE_VM(59),
    USER_CHANGE_DISK_VM(38),
    USER_FAILED_CHANGE_DISK_VM(102),
    USER_CHANGE_FLOPPY_VM(72),
    USER_FAILED_CHANGE_FLOPPY_VM(75),
    USER_PAUSE_VM(39),
    USER_FAILED_PAUSE_VM(55),
    USER_SUSPEND_VM(501),
    @Deprecated
    USER_SUSPEND_VM_FINISH_SUCCESS(512),
    USER_SUSPEND_VM_FINISH_FAILURE(521),
    USER_SUSPEND_VM_FINISH_FAILURE_WILL_TRY_AGAIN(532),
    USER_FAILED_SUSPEND_VM(502),
    USER_SUSPEND_VM_OK(503),
    USER_RESUME_VM(40),
    USER_FAILED_RESUME_VM(103),
    USER_INITIATED_SHUTDOWN_VM(73),
    USER_FAILED_SHUTDOWN_VM(74),
    USER_STOPPED_VM_INSTEAD_OF_SHUTDOWN(76),
    USER_FAILED_STOPPING_VM_INSTEAD_OF_SHUTDOWN(77),
    USER_ADD_DISK_TO_VM(78),
    USER_ADD_DISK_TO_VM_FINISHED_SUCCESS(97),
    USER_ADD_DISK_TO_VM_FINISHED_FAILURE(98),
    USER_FAILED_ADD_DISK_TO_VM(79),
    @Deprecated
    USER_REMOVE_DISK_FROM_VM(80),
    @Deprecated
    USER_FAILED_REMOVE_DISK_FROM_VM(81),
    USER_MOVED_VM(82),
    USER_MOVED_VM_FINISHED_SUCCESS(91),
    USER_MOVED_VM_FINISHED_FAILURE(92),
    USER_FAILED_MOVE_VM(83),
    USER_MOVED_TEMPLATE(84),
    USER_MOVED_TEMPLATE_FINISHED_SUCCESS(93),
    USER_MOVED_TEMPLATE_FINISHED_FAILURE(94),
    USER_FAILED_MOVE_TEMPLATE(85),
    USER_COPIED_TEMPLATE(86),
    USER_COPIED_TEMPLATE_FINISHED_SUCCESS(95),
    USER_COPIED_TEMPLATE_FINISHED_FAILURE(96),
    USER_FAILED_COPY_TEMPLATE(87),
    USER_UPDATE_VM_DISK(88),
    USER_FAILED_UPDATE_VM_DISK(89),
    USER_HOTPLUG_DISK(2000),
    USER_FAILED_HOTPLUG_DISK(2001),
    USER_HOTUNPLUG_DISK(2002),
    USER_FAILED_HOTUNPLUG_DISK(2003),
    USER_COPIED_TEMPLATE_DISK(2004),
    USER_FAILED_COPY_TEMPLATE_DISK(2005),
    USER_COPIED_TEMPLATE_DISK_FINISHED_SUCCESS(2006),
    USER_COPIED_TEMPLATE_DISK_FINISHED_FAILURE(2007),
    USER_MOVED_VM_DISK(2008),
    USER_FAILED_MOVED_VM_DISK(2009),
    USER_MOVED_VM_DISK_FINISHED_SUCCESS(2010),
    USER_MOVED_VM_DISK_FINISHED_FAILURE(2011),
    USER_FINISHED_REMOVE_DISK(2014),
    USER_FINISHED_FAILED_REMOVE_DISK(2015),
    USER_ATTACH_DISK_TO_VM(2016),
    USER_FAILED_ATTACH_DISK_TO_VM(2017),
    USER_DETACH_DISK_FROM_VM(2018),
    USER_FAILED_DETACH_DISK_FROM_VM(2019),
    USER_ADD_DISK(2020),
    USER_ADD_DISK_FINISHED_SUCCESS(2021),
    USER_ADD_DISK_FINISHED_FAILURE(2022),
    USER_FAILED_ADD_DISK(2023),
    USER_IMPORT_IMAGE(2027),
    USER_IMPORT_IMAGE_FINISHED_SUCCESS(2028),
    USER_IMPORT_IMAGE_FINISHED_FAILURE(2029),
    USER_EXPORT_IMAGE(2030),
    USER_EXPORT_IMAGE_FINISHED_SUCCESS(2031),
    USER_EXPORT_IMAGE_FINISHED_FAILURE(2032),
    HOT_SET_NUMBER_OF_CPUS(2033),
    FAILED_HOT_SET_NUMBER_OF_CPUS(2034),

    // Used only from SQL script, therefor should not have severity & message
    USER_RUN_UNLOCK_ENTITY_SCRIPT(2024),
    USER_MOVE_IMAGE_GROUP_FAILED_TO_DELETE_SRC_IMAGE(2025),
    USER_MOVE_IMAGE_GROUP_FAILED_TO_DELETE_DST_IMAGE(2026),
    // Quota audit logs
    USER_ADD_QUOTA(3000),
    USER_FAILED_ADD_QUOTA(3001),
    USER_UPDATE_QUOTA(3002),
    USER_FAILED_UPDATE_QUOTA(3003),
    USER_DELETE_QUOTA(3004),
    USER_FAILED_DELETE_QUOTA(3005),
    USER_EXCEEDED_QUOTA_VDS_GROUP_GRACE_LIMIT(3006),
    USER_EXCEEDED_QUOTA_VDS_GROUP_LIMIT(3007),
    USER_EXCEEDED_QUOTA_VDS_GROUP_THRESHOLD(3008),
    USER_EXCEEDED_QUOTA_STORAGE_GRACE_LIMIT(3009),
    USER_EXCEEDED_QUOTA_STORAGE_LIMIT(3010),
    USER_EXCEEDED_QUOTA_STORAGE_THRESHOLD(3011),
    QUOTA_STORAGE_RESIZE_LOWER_THEN_CONSUMPTION(3012),
    MISSING_QUOTA_STORAGE_PARAMETERS_PERMISSIVE_MODE(3013),
    MISSING_QUOTA_CLUSTER_PARAMETERS_PERMISSIVE_MODE(3014),
    USER_EXCEEDED_QUOTA_VDS_GROUP_GRACE_LIMIT_PERMISSIVE_MODE(3015),
    USER_EXCEEDED_QUOTA_STORAGE_GRACE_LIMIT_PERMISSIVE_MODE(3016),

    // Gluster Audit Logs
    GLUSTER_VOLUME_CREATE(4000),
    GLUSTER_VOLUME_CREATE_FAILED(4001),
    GLUSTER_VOLUME_OPTION_ADDED(4002),
    GLUSTER_VOLUME_OPTION_SET_FAILED(4003),
    GLUSTER_VOLUME_START(4004),
    GLUSTER_VOLUME_START_FAILED(4005),
    GLUSTER_VOLUME_STOP(4006),
    GLUSTER_VOLUME_STOP_FAILED(4007),
    GLUSTER_VOLUME_OPTIONS_RESET(4008),
    GLUSTER_VOLUME_OPTIONS_RESET_FAILED(4009),
    GLUSTER_VOLUME_DELETE(4010),
    GLUSTER_VOLUME_DELETE_FAILED(4011),
    GLUSTER_VOLUME_REBALANCE_START(4012),
    GLUSTER_VOLUME_REBALANCE_START_FAILED(4013),
    GLUSTER_VOLUME_REMOVE_BRICKS(4014),
    GLUSTER_VOLUME_REMOVE_BRICKS_FAILED(4015),
    GLUSTER_VOLUME_REPLACE_BRICK_FAILED(4016),
    GLUSTER_VOLUME_REPLACE_BRICK_START(4017),
    GLUSTER_VOLUME_REPLACE_BRICK_START_FAILED(4018),
    GLUSTER_VOLUME_ADD_BRICK(4019),
    GLUSTER_VOLUME_ADD_BRICK_FAILED(4020),
    GLUSTER_SERVER_REMOVE_FAILED(4021),
    GLUSTER_VOLUME_PROFILE_START(4022),
    GLUSTER_VOLUME_PROFILE_START_FAILED(4023),
    GLUSTER_VOLUME_PROFILE_STOP(4024),
    GLUSTER_VOLUME_PROFILE_STOP_FAILED(4025),
    GLUSTER_VOLUME_CREATED_FROM_CLI(4026),
    GLUSTER_VOLUME_DELETED_FROM_CLI(4027),
    GLUSTER_VOLUME_OPTION_SET_FROM_CLI(4028),
    GLUSTER_VOLUME_OPTION_RESET_FROM_CLI(4029),
    GLUSTER_VOLUME_PROPERTIES_CHANGED_FROM_CLI(4030),
    GLUSTER_VOLUME_BRICK_ADDED_FROM_CLI(4031),
    GLUSTER_VOLUME_BRICK_REMOVED_FROM_CLI(4032),
    GLUSTER_SERVER_REMOVED_FROM_CLI(4033),
    GLUSTER_VOLUME_INFO_FAILED(4034),
    GLUSTER_COMMAND_FAILED(4035),
    GLUSTER_SERVER_ADD_FAILED(4436),
    GLUSTER_SERVERS_LIST_FAILED(4437),
    GLUSTER_SERVER_REMOVE(4038),
    GLUSTER_VOLUME_STARTED_FROM_CLI(4039),
    GLUSTER_VOLUME_STOPPED_FROM_CLI(4040),
    GLUSTER_VOLUME_OPTION_CHANGED_FROM_CLI(4041),
    GLUSTER_HOOK_ENABLE(4042),
    GLUSTER_HOOK_ENABLE_FAILED(4043),
    GLUSTER_HOOK_ENABLE_PARTIAL(4044),
    GLUSTER_HOOK_DISABLE(4045),
    GLUSTER_HOOK_DISABLE_FAILED(4046),
    GLUSTER_HOOK_DISABLE_PARTIAL(4047),
    GLUSTER_HOOK_LIST_FAILED(4048),
    GLUSTER_HOOK_CONFLICT_DETECTED(4049),
    GLUSTER_HOOK_DETECTED_NEW(4050),
    GLUSTER_HOOK_DETECTED_DELETE(4051),
    GLUSTER_VOLUME_OPTION_MODIFIED(4052),
    GLUSTER_HOOK_GETCONTENT_FAILED(4053),
    GLUSTER_SERVICES_LIST_FAILED(4054),
    GLUSTER_SERVICE_TYPE_ADDED_TO_CLUSTER(4055),
    GLUSTER_CLUSTER_SERVICE_STATUS_CHANGED(4056),
    GLUSTER_SERVICE_ADDED_TO_SERVER(4057),
    GLUSTER_SERVER_SERVICE_STATUS_CHANGED(4058),
    GLUSTER_HOOK_UPDATED(4059),
    GLUSTER_HOOK_UPDATE_FAILED(4060),
    GLUSTER_HOOK_ADDED(4061),
    GLUSTER_HOOK_ADD_FAILED(4062),
    GLUSTER_HOOK_REMOVED(4063),
    GLUSTER_HOOK_REMOVE_FAILED(4064),
    GLUSTER_HOOK_REFRESH(4065),
    GLUSTER_HOOK_REFRESH_FAILED(4066),
    GLUSTER_SERVICE_STARTED(4067),
    GLUSTER_SERVICE_START_FAILED(4068),
    GLUSTER_SERVICE_STOPPED(4069),
    GLUSTER_SERVICE_STOP_FAILED(4070),
    GLUSTER_SERVICES_LIST_NOT_FETCHED(4071),
    GLUSTER_SERVICE_RESTARTED(4072),
    GLUSTER_SERVICE_RESTART_FAILED(4073),
    GLUSTER_VOLUME_OPTIONS_RESET_ALL(4074),
    GLUSTER_HOST_UUID_NOT_FOUND(4075),
    GLUSTER_VOLUME_BRICK_ADDED(4076),
    GLUSTER_CLUSTER_SERVICE_STATUS_ADDED(4077),
    GLUSTER_VOLUME_REBALANCE_STOP(4078),
    GLUSTER_VOLUME_REBALANCE_STOP_FAILED(4079),
    START_REMOVING_GLUSTER_VOLUME_BRICKS(4080),
    START_REMOVING_GLUSTER_VOLUME_BRICKS_FAILED(4081),
    GLUSTER_VOLUME_REMOVE_BRICKS_STOP(4082),
    GLUSTER_VOLUME_REMOVE_BRICKS_STOP_FAILED(4083),
    GLUSTER_VOLUME_REMOVE_BRICKS_COMMIT(4084),
    GLUSTER_VOLUME_REMOVE_BRICKS_COMMIT_FAILED(4085),
    GLUSTER_BRICK_STATUS_CHANGED(4086),
    GLUSTER_VOLUME_REBALANCE_FINISHED(4087),
    GLUSTER_VOLUME_MIGRATE_BRICK_DATA_FINISHED(4088),
    GLUSTER_HOST_UUID_ALREADY_EXISTS(4089),
    GLUSTER_VOLUME_REBALANCE_START_DETECTED_FROM_CLI(4089),
    START_REMOVING_GLUSTER_VOLUME_BRICKS_DETECTED_FROM_CLI(4090),
    GLUSTER_VOLUME_REBALANCE_NOT_FOUND_FROM_CLI(4091),
    REMOVE_GLUSTER_VOLUME_BRICKS_NOT_FOUND_FROM_CLI(4092),

    USER_FORCE_SELECTED_SPM(159),
    USER_VDS_RESTART(41),
    USER_FAILED_VDS_RESTART(107),
    USER_VDS_START(20),
    USER_FAILED_VDS_START(118),
    USER_VDS_STOP(21),
    USER_FAILED_VDS_STOP(137),
    USER_ADD_VDS(42),
    USER_FAILED_ADD_VDS(104),
    USER_UPDATE_VDS(43),
    USER_FAILED_UPDATE_VDS(105),
    USER_REMOVE_VDS(44),
    USER_FAILED_REMOVE_VDS(106),
    USER_CREATE_SNAPSHOT(45),
    USER_CREATE_SNAPSHOT_FINISHED_SUCCESS(68),
    USER_CREATE_SNAPSHOT_FINISHED_FAILURE(69),
    USER_CREATE_LIVE_SNAPSHOT_FINISHED_FAILURE(170),
    USER_FAILED_CREATE_SNAPSHOT(117),
    USER_CREATE_LIVE_SNAPSHOT_NO_MEMORY_FAILURE(173),
    USER_REMOVE_SNAPSHOT(342),
    USER_FAILED_REMOVE_SNAPSHOT(343),
    USER_REMOVE_SNAPSHOT_FINISHED_SUCCESS(356),
    USER_REMOVE_SNAPSHOT_FINISHED_FAILURE(357),
    USER_TRY_BACK_TO_SNAPSHOT(46),
    USER_TRY_BACK_TO_SNAPSHOT_FINISH_SUCCESS(71),
    USER_TRY_BACK_TO_SNAPSHOT_FINISH_FAILURE(99),
    USER_FAILED_TRY_BACK_TO_SNAPSHOT(115),
    USER_RESTORE_FROM_SNAPSHOT(47),
    USER_RESTORE_FROM_SNAPSHOT_START(1190),
    USER_RESTORE_FROM_SNAPSHOT_FINISH_SUCCESS(100),
    USER_RESTORE_FROM_SNAPSHOT_FINISH_FAILURE(101),
    USER_FAILED_RESTORE_FROM_SNAPSHOT(116),
    USER_ADD_VM_TEMPLATE(48),
    USER_ADD_VM_TEMPLATE_FINISHED_SUCCESS(51),
    USER_ADD_VM_TEMPLATE_FINISHED_FAILURE(52),
    USER_FAILED_ADD_VM_TEMPLATE(108),
    USER_UPDATE_VM_TEMPLATE(49),
    USER_FAILED_UPDATE_VM_TEMPLATE(109),
    USER_REMOVE_VM_TEMPLATE(50),
    USER_REMOVE_VM_TEMPLATE_FINISHED(251),
    USER_FAILED_REMOVE_VM_TEMPLATE(110),
    TEMPLATE_IMPORT(135),
    TEMPLATE_IMPORT_FAILED(136),
    USER_ATTACH_USER_TO_VM(520),
    USER_DETACH_USER_FROM_VM(360),
    USER_FAILED_DETACH_USER_FROM_VM(361),
    USER_FAILED_ATTACH_USER_TO_VM(182),
    USER_REMOVE_ADUSER(325),
    USER_FAILED_REMOVE_ADUSER(326),
    USER_FAILED_ADD_ADUSER(327),
    USER_PASSWORD_CHANGED(346),
    USER_PASSWORD_CHANGE_FAILED(347),
    USER_CLEAR_UNKNOWN_VMS(348),
    USER_FAILED_CLEAR_UNKNOWN_VMS(349),
    USER_EXTEND_DISK_SIZE_FAILURE(370),
    USER_EXTEND_DISK_SIZE_SUCCESS(371),
    USER_EXTEND_DISK_SIZE_UPDATE_VM_FAILURE(372),

    USER_EJECT_VM_DISK(528),
    USER_EJECT_VM_FLOPPY(529),

    // -- VM Log types --
    VM_DOWN(61),
    VM_DOWN_ERROR(119),
    VM_MIGRATION_START(62),
    VM_MIGRATION_DONE(63),
    VM_MIGRATION_ABORT(64),
    VM_MIGRATION_FAILED(65),
    VM_MIGRATION_FAILED_NO_VDS_TO_RUN_ON(166),
    VM_FAILURE(66),
    VM_MIGRATION_START_SYSTEM_INITIATED(67),
    VM_MIGRATION_FAILED_FROM_TO(120),
    VM_MIGRATION_TRYING_RERUN(128),
    VM_CANCEL_MIGRATION(161),
    VM_CANCEL_MIGRATION_FAILED(162),
    VM_STATUS_RESTORED(163),
    VM_SET_TICKET(164),
    VM_SET_TICKET_FAILED(165),
    VM_CONSOLE_CONNECTED(167),
    VM_CONSOLE_DISCONNECTED(168),

    VM_MIGRATION_FAILED_DURING_MOVE_TO_MAINTENANCE(140),
    VM_SET_TO_UNKNOWN_STATUS(142),
    VM_WAS_SET_DOWN_DUE_TO_HOST_REBOOT_OR_MANUAL_FENCE(143),

    USER_EXPORT_VM(131),
    USER_EXPORT_VM_FAILED(132),
    USER_EXPORT_TEMPLATE(133),
    USER_EXPORT_TEMPLATE_FAILED(134),
    VM_IMPORT(124),
    VM_IMPORT_FAILED(125),
    VM_NOT_RESPONDING(126),
    VDS_RUN_IN_NO_KVM_MODE(127),
    VDS_VERSION_NOT_SUPPORTED_FOR_CLUSTER(141),
    VDS_CLUSTER_VERSION_NOT_SUPPORTED(154),
    VDS_ARCHITECTURE_NOT_SUPPORTED_FOR_CLUSTER(155),
    CPU_TYPE_UNSUPPORTED_IN_THIS_CLUSTER_VERSION(156),
    VM_CLEARED(129),
    VM_PAUSED_ENOSPC(138),
    VM_PAUSED_ERROR(139),
    VM_IMPORT_INFO(144),
    VM_PAUSED_EIO(145),
    VM_PAUSED_EPERM(146),
    VM_POWER_DOWN_FAILED(147),
    VM_IMPORT_FROM_CONFIGURATION_EXECUTED_SUCCESSFULLY(148),
    VM_IMPORT_FROM_CONFIGURATION_ATTACH_DISKS_FAILED(149),

    VM_MEMORY_UNDER_GUARANTEED_VALUE(148, AuditLogTimeInterval.MINUTE.getValue() * 15),
    VM_BALLOON_DRIVER_ERROR(149, AuditLogTimeInterval.MINUTE.getValue() * 15),
    VM_BALLOON_DRIVER_UNCONTROLLED(150, AuditLogTimeInterval.MINUTE.getValue() * 15),
    VM_MEMORY_NOT_IN_RECOMMENDED_RANGE(151),

    USER_ADD_VM_POOL(300),
    USER_ADD_VM_POOL_FAILED(301),
    USER_ADD_VM_POOL_WITH_VMS(302),
    USER_ADD_VM_POOL_WITH_VMS_FAILED(303),
    USER_ADD_VM_POOL_WITH_VMS_ADD_VDS_FAILED(320),
    USER_REMOVE_VM_POOL(304),
    USER_REMOVE_VM_POOL_FAILED(305),
    USER_ADD_VM_TO_POOL(306),
    USER_ADD_VM_TO_POOL_FAILED(307),
    USER_REMOVE_VM_FROM_POOL(308),
    USER_REMOVE_VM_FROM_POOL_FAILED(309),
    USER_ATTACH_USER_TO_POOL(310),
    USER_ATTACH_USER_TO_POOL_INTERNAL(472),
    USER_ATTACH_USER_TO_POOL_FAILED(311),
    USER_ATTACH_USER_TO_POOL_FAILED_INTERNAL(473),
    USER_DETACH_USER_FROM_POOL(312),
    USER_DETACH_USER_FROM_POOL_FAILED(313),
    USER_UPDATE_VM_POOL(314),
    USER_UPDATE_VM_POOL_FAILED(315),
    USER_ATTACH_USER_TO_VM_FROM_POOL(316),
    USER_ATTACH_USER_TO_VM_FROM_POOL_FINISHED_SUCCESS(318),
    USER_ATTACH_USER_TO_VM_FROM_POOL_FINISHED_FAILURE(319),
    USER_ATTACH_USER_TO_VM_FROM_POOL_FAILED(317),
    USER_UPDATE_VM_POOL_WITH_VMS(344),
    USER_UPDATE_VM_POOL_WITH_VMS_FAILED(345),
    USER_VM_POOL_MAX_SUBSEQUENT_FAILURES_REACHED(358),

    USER_ADD_BOOKMARK(350),
    USER_ADD_BOOKMARK_FAILED(351),
    USER_UPDATE_BOOKMARK(352),
    USER_UPDATE_BOOKMARK_FAILED(353),
    USER_REMOVE_BOOKMARK(354),
    USER_REMOVE_BOOKMARK_FAILED(355),
    /**
     * AdGroups
     */
    USER_ATTACH_VM_TO_AD_GROUP(400),
    USER_ATTACH_VM_TO_AD_GROUP_FAILED(401),
    USER_DETACH_VM_TO_AD_GROUP(402),
    USER_DETACH_VM_TO_AD_GROUP_FAILED(403),
    USER_ATTACH_VM_POOL_TO_AD_GROUP(404),
    USER_ATTACH_VM_POOL_TO_AD_GROUP_INTERNAL(470),
    USER_ATTACH_VM_POOL_TO_AD_GROUP_FAILED(405),
    USER_ATTACH_VM_POOL_TO_AD_GROUP_FAILED_INTERNAL(471),
    USER_DETACH_VM_POOL_TO_AD_GROUP(406),
    USER_DETACH_VM_POOL_TO_AD_GROUP_FAILED(407),
    USER_REMOVE_AD_GROUP(408),
    USER_REMOVE_AD_GROUP_FAILED(409),
    USER_UPDATE_TAG(430),
    USER_UPDATE_TAG_FAILED(431),
    USER_ADD_TAG(432),
    USER_ADD_TAG_FAILED(433),
    USER_REMOVE_TAG(434),
    USER_REMOVE_TAG_FAILED(435),
    USER_ATTACH_TAG_TO_USER(436),
    USER_ATTACH_TAG_TO_USER_FAILED(437),
    USER_ATTACH_TAG_TO_USER_GROUP(438),
    USER_ATTACH_TAG_TO_USER_GROUP_FAILED(439),
    USER_ATTACH_TAG_TO_VM(440),
    USER_ATTACH_TAG_TO_VM_FAILED(441),
    USER_ATTACH_TAG_TO_VDS(442),
    USER_ATTACH_TAG_TO_VDS_FAILED(443),
    USER_DETACH_VDS_FROM_TAG(444),
    USER_DETACH_VDS_FROM_TAG_FAILED(445),
    USER_DETACH_VM_FROM_TAG(446),
    USER_DETACH_VM_FROM_TAG_FAILED(447),
    USER_DETACH_USER_FROM_TAG(448),
    USER_DETACH_USER_FROM_TAG_FAILED(449),
    USER_DETACH_USER_GROUP_FROM_TAG(450),
    USER_DETACH_USER_GROUP_FROM_TAG_FAILED(451),
    USER_ATTACH_TAG_TO_USER_EXISTS(452),
    USER_ATTACH_TAG_TO_USER_GROUP_EXISTS(453),
    USER_ATTACH_TAG_TO_VM_EXISTS(454),
    USER_ATTACH_TAG_TO_VDS_EXISTS(455),
    USER_MOVE_TAG(555),
    USER_MOVE_TAG_FAILED(556),
    USER_LOGGED_IN_VM(456),
    USER_LOGGED_OUT_VM(457),
    USER_LOCKED_VM(458),
    USER_UNLOCKED_VM(459),
    UPDATE_TAGS_VM_DEFAULT_DISPLAY_TYPE(467),
    UPDATE_TAGS_VM_DEFAULT_DISPLAY_TYPE_FAILED(468),
    USER_ADD_VDS_GROUP(809),
    USER_ADD_VDS_GROUP_FAILED(810),
    USER_UPDATE_VDS_GROUP(811),
    USER_UPDATE_VDS_GROUP_FAILED(812),
    SYSTEM_UPDATE_VDS_GROUP(835, AuditLogTimeInterval.MINUTE.getValue()),
    SYSTEM_UPDATE_VDS_GROUP_FAILED(836, AuditLogTimeInterval.MINUTE.getValue()),
    USER_REMOVE_VDS_GROUP(813),
    USER_REMOVE_VDS_GROUP_FAILED(814),
    MAC_POOL_EMPTY(816),
    MAC_ADDRESS_IS_IN_USE(833),
    MAC_ADDRESS_IS_IN_USE_UNPLUG(838),
    CERTIFICATE_FILE_NOT_FOUND(817),
    RUN_VM_FAILED(818),
    MAC_ADDRESSES_POOL_NOT_INITIALIZED(837, AuditLogTimeInterval.HOUR.getValue()),
    // CBC
    VDS_REGISTER_ERROR_UPDATING_HOST(819),
    VDS_REGISTER_ERROR_UPDATING_HOST_ALL_TAKEN(820),
    VDS_REGISTER_HOST_IS_ACTIVE(821),
    VDS_REGISTER_ERROR_UPDATING_NAME(822),
    VDS_REGISTER_ERROR_UPDATING_NAMES_ALL_TAKEN(823),
    VDS_REGISTER_NAME_IS_ACTIVE(824),
    VDS_REGISTER_AUTO_APPROVE_PATTERN(825),
    VDS_REGISTER_FAILED(826),
    VDS_REGISTER_EXISTING_VDS_UPDATE_FAILED(827),
    VDS_REGISTER_SUCCEEDED(828),
    VDS_REGISTER_EMPTY_ID(834),
    VM_MIGRATION_ON_CONNECT_CHECK_FAILED(829),
    VM_MIGRATION_ON_CONNECT_CHECK_SUCCEEDED(830),

    // Network
    NETWORK_ATTACH_NETWORK_TO_VDS(920),
    NETWORK_ATTACH_NETWORK_TO_VDS_FAILED(921),
    NETWORK_DETACH_NETWORK_FROM_VDS(922),
    NETWORK_DETACH_NETWORK_FROM_VDS_FAILED(923),
    NETWORK_ADD_BOND(924),
    NETWORK_ADD_BOND_FAILED(925),
    NETWORK_REMOVE_BOND(926),
    NETWORK_REMOVE_BOND_FAILED(927),
    NETWORK_VDS_NETWORK_MATCH_CLUSTER(928),
    NETWORK_VDS_NETWORK_NOT_MATCH_CLUSTER(929),
    NETWORK_REMOVE_VM_INTERFACE(930),
    NETWORK_REMOVE_VM_INTERFACE_FAILED(931),
    NETWORK_ADD_VM_INTERFACE(932),
    NETWORK_ADD_VM_INTERFACE_FAILED(933),
    NETWORK_UPDATE_VM_INTERFACE(934),
    NETWORK_UPDATE_VM_INTERFACE_FAILED(935),
    NETWORK_ADD_TEMPLATE_INTERFACE(936),
    NETWORK_ADD_TEMPLATE_INTERFACE_FAILED(937),
    NETWORK_REMOVE_TEMPLATE_INTERFACE(938),
    NETWORK_REMOVE_TEMPLATE_INTERFACE_FAILED(939),
    NETWORK_UPDATE_TEMPLATE_INTERFACE(940),
    NETWORK_UPDATE_TEMPLATE_INTERFACE_FAILED(941),
    NETWORK_ADD_NETWORK(942),
    NETWORK_ADD_NETWORK_FAILED(943),
    NETWORK_REMOVE_NETWORK(944),
    NETWORK_REMOVE_NETWORK_FAILED(945),
    NETWORK_ATTACH_NETWORK_TO_VDS_GROUP(946),
    NETWORK_ATTACH_NETWORK_TO_VDS_GROUP_FAILED(947),
    NETWORK_DETACH_NETWORK_TO_VDS_GROUP(948),
    NETWORK_DETACH_NETWORK_TO_VDS_GROUP_FAILED(949),
    NETWORK_ACTIVATE_VM_INTERFACE_SUCCESS(1012),
    NETWORK_ACTIVATE_VM_INTERFACE_FAILURE(1013),
    NETWORK_DEACTIVATE_VM_INTERFACE_SUCCESS(1014),
    NETWORK_DEACTIVATE_VM_INTERFACE_FAILURE(1015),
    NETWORK_UPDATE_DISPLAY_TO_VDS_GROUP(1100),
    NETWORK_UPDATE_DISPLAY_TO_VDS_GROUP_FAILED(1101),
    NETWORK_UPDATE_NETWORK_TO_VDS_INTERFACE(1102),
    NETWORK_UPDATE_NETWORK_TO_VDS_INTERFACE_FAILED(1103),
    NETWORK_COMMINT_NETWORK_CHANGES(1104),
    NETWORK_COMMINT_NETWORK_CHANGES_FAILED(1105),
    @Deprecated
    NETWORK_HOST_USING_WRONG_CLUSER_VLAN(1106),
    @Deprecated
    NETWORK_HOST_MISSING_CLUSER_VLAN(1107),
    VDS_NETWORK_MTU_DIFFER_FROM_LOGICAL_NETWORK(1108, AuditLogTimeInterval.HOUR.getValue()),
    BRIDGED_NETWORK_OVER_MULTIPLE_INTERFACES(1109),
    VDS_NETWORKS_OUT_OF_SYNC(1110),
    NETWORK_UPDTAE_NETWORK_ON_CLUSTER(1112),
    NETWORK_UPDTAE_NETWORK_ON_CLUSTER_FAILED(1113),
    NETWORK_UPDATE_NETWORK(1114),
    NETWORK_UPDATE_NETWORK_FAILED(1115),
    NETWORK_UPDATE_VM_INTERFACE_LINK_UP(1116),
    NETWORK_UPDATE_VM_INTERFACE_LINK_DOWN(1117),
    INVALID_INTERFACE_FOR_MANAGEMENT_NETWORK_CONFIGURATION(1118),
    VLAN_ID_MISMATCH_FOR_MANAGEMENT_NETWORK_CONFIGURATION(1119),
    SETUP_NETWORK_FAILED_FOR_MANAGEMENT_NETWORK_CONFIGURATION(1120),
    PERSIST_NETWORK_FAILED_FOR_MANAGEMENT_NETWORK(1121),
    ADD_VNIC_PROFILE(1122),
    ADD_VNIC_PROFILE_FAILED(1123),
    UPDATE_VNIC_PROFILE(1124),
    UPDATE_VNIC_PROFILE_FAILED(1125),
    REMOVE_VNIC_PROFILE(1126),
    REMOVE_VNIC_PROFILE_FAILED(1127),
    NETWORK_WITHOUT_INTERFACES(1128),
    VNIC_PROFILE_UNSUPPORTED_FEATURES(1129, AuditLogTimeInterval.DAY.getValue()),
    ADD_NETWORK_BY_LABEL_FAILED(1130),
    REMOVE_NETWORK_BY_LABEL_FAILED(1131),
    LABEL_NETWORK(1132),
    LABEL_NETWORK_FAILED(1133),
    UNLABEL_NETWORK(1134),
    UNLABEL_NETWORK_FAILED(1135),
    LABEL_NIC(1136),
    LABEL_NIC_FAILED(1137),
    UNLABEL_NIC(1138),
    UNLABEL_NIC_FAILED(1139),
    SUBNET_REMOVED(1140),
    SUBNET_REMOVAL_FAILED(1141),
    SUBNET_ADDED(1142),
    SUBNET_ADDITION_FAILED(1143),
    CONFIGURE_NETWORK_BY_LABELS_WHEN_CHANGING_CLUSTER_FAILED(1144),

    // Import/Export
    IMPORTEXPORT_STARTING_EXPORT_VM(1162),
    IMPORTEXPORT_EXPORT_VM(1150),
    IMPORTEXPORT_EXPORT_VM_FAILED(1151),
    IMPORTEXPORT_STARTING_IMPORT_VM(1165),
    IMPORTEXPORT_IMPORT_VM(1152),
    IMPORTEXPORT_IMPORT_VM_FAILED(1153),
    IMPORTEXPORT_STARTING_REMOVE_TEMPLATE(1166),
    IMPORTEXPORT_REMOVE_TEMPLATE(1154),
    IMPORTEXPORT_REMOVE_TEMPLATE_FAILED(1155),
    IMPORTEXPORT_EXPORT_TEMPLATE(1156),
    IMPORTEXPORT_STARTING_EXPORT_TEMPLATE(1164),
    IMPORTEXPORT_EXPORT_TEMPLATE_FAILED(1157),
    IMPORTEXPORT_STARTING_IMPORT_TEMPLATE(1163),
    IMPORTEXPORT_IMPORT_TEMPLATE(1158),
    IMPORTEXPORT_IMPORT_TEMPLATE_FAILED(1159),
    IMPORTEXPORT_STARTING_REMOVE_VM(1167),
    IMPORTEXPORT_REMOVE_VM(1160),
    IMPORTEXPORT_REMOVE_VM_FAILED(1161),
    IMPORTEXPORT_GET_VMS_INFO_FAILED(1162, AuditLogTimeInterval.MINUTE.getValue() * 30),
    IMPORTEXPORT_FAILED_TO_IMPORT_VM(1168),
    IMPORTEXPORT_FAILED_TO_IMPORT_TEMPLATE(1169),
    IMPORTEXPORT_IMPORT_TEMPLATE_INVALID_INTERFACES(1170, AuditLogTimeInterval.MINUTE.getValue()),

    USER_ADD_PERMISSION(850),
    USER_ADD_PERMISSION_FAILED(851),
    USER_REMOVE_PERMISSION(852),
    USER_REMOVE_PERMISSION_FAILED(853),
    USER_ADD_ROLE(854),
    USER_ADD_ROLE_FAILED(855),
    USER_UPDATE_ROLE(856),
    USER_UPDATE_ROLE_FAILED(857),
    USER_REMOVE_ROLE(858),
    USER_REMOVE_ROLE_FAILED(859),
    USER_ATTACHED_ACTION_GROUP_TO_ROLE(860),
    USER_ATTACHED_ACTION_GROUP_TO_ROLE_FAILED(861),
    USER_DETACHED_ACTION_GROUP_FROM_ROLE(862),
    USER_DETACHED_ACTION_GROUP_FROM_ROLE_FAILED(863),
    USER_ADD_ROLE_WITH_ACTION_GROUP(864),
    USER_ADD_ROLE_WITH_ACTION_GROUP_FAILED(865),
    USER_ADD_SYSTEM_PERMISSION(866),
    USER_ADD_SYSTEM_PERMISSION_FAILED(867),
    USER_REMOVE_SYSTEM_PERMISSION(868),
    USER_REMOVE_SYSTEM_PERMISSION_FAILED(869),

    // AD Computer Account
    AD_COMPUTER_ACCOUNT_SUCCEEDED(900),
    AD_COMPUTER_ACCOUNT_FAILED(901),
    USER_ADD_STORAGE_POOL(950),
    USER_ADD_STORAGE_POOL_FAILED(951),
    USER_UPDATE_STORAGE_POOL(952),
    USER_UPDATE_STORAGE_POOL_FAILED(953),
    USER_REMOVE_STORAGE_POOL(954),
    USER_REMOVE_STORAGE_POOL_FAILED(955),
    USER_FORCE_REMOVE_STORAGE_POOL(918),
    USER_FORCE_REMOVE_STORAGE_POOL_FAILED(919),
    USER_ADD_STORAGE_DOMAIN(956),
    USER_ADD_STORAGE_DOMAIN_FAILED(957),
    USER_UPDATE_STORAGE_DOMAIN(958),
    USER_UPDATE_STORAGE_DOMAIN_FAILED(959),
    USER_REMOVE_STORAGE_DOMAIN(960),
    USER_REMOVE_STORAGE_DOMAIN_FAILED(961),
    USER_ATTACH_STORAGE_DOMAIN_TO_POOL(962),
    USER_ATTACH_STORAGE_DOMAIN_TO_POOL_FAILED(963),
    USER_DETACH_STORAGE_DOMAIN_FROM_POOL(964),
    USER_DETACH_STORAGE_DOMAIN_FROM_POOL_FAILED(965),
    USER_ACTIVATED_STORAGE_DOMAIN(966),
    USER_ACTIVATE_STORAGE_DOMAIN_FAILED(967),
    USER_DEACTIVATED_STORAGE_DOMAIN(968),
    USER_DEACTIVATE_STORAGE_DOMAIN_FAILED(969),
    SYSTEM_DEACTIVATED_STORAGE_DOMAIN(970, AuditLogTimeInterval.MINUTE.getValue()),
    SYSTEM_DEACTIVATE_STORAGE_DOMAIN_FAILED(971, AuditLogTimeInterval.MINUTE.getValue()),
    USER_EXTENDED_STORAGE_DOMAIN(972),
    USER_EXTENDED_STORAGE_DOMAIN_FAILED(973),
    @Deprecated
    USER_REMOVE_VG(974),
    @Deprecated
    USER_REMOVE_VG_FAILED(975),
    USER_ACTIVATE_STORAGE_POOL(976),
    USER_ACTIVATE_STORAGE_POOL_FAILED(977),
    SYSTEM_FAILED_CHANGE_STORAGE_POOL_STATUS(978, AuditLogTimeInterval.MINUTE.getValue()),
    SYSTEM_CHANGE_STORAGE_POOL_STATUS_NO_HOST_FOR_SPM(979, AuditLogTimeInterval.MINUTE.getValue()),
    SYSTEM_CHANGE_STORAGE_POOL_STATUS_PROBLEMATIC(980, AuditLogTimeInterval.MINUTE.getValue()),

    USER_FORCE_REMOVE_STORAGE_DOMAIN(981),
    USER_FORCE_REMOVE_STORAGE_DOMAIN_FAILED(982),
    RECONSTRUCT_MASTER_FAILED_NO_MASTER(983, AuditLogTimeInterval.HOUR.getValue()),
    RECONSTRUCT_MASTER_DONE(984),
    RECONSTRUCT_MASTER_FAILED(985, AuditLogTimeInterval.HOUR.getValue()),
    SYSTEM_CHANGE_STORAGE_POOL_STATUS_PROBLEMATIC_SEARCHING_NEW_SPM(986, AuditLogTimeInterval.MINUTE.getValue()),
    SYSTEM_CHANGE_STORAGE_POOL_STATUS_PROBLEMATIC_WITH_ERROR(987, AuditLogTimeInterval.MINUTE.getValue()),
    USER_CONNECT_HOSTS_TO_LUN_FAILED(988),
    SYSTEM_CHANGE_STORAGE_POOL_STATUS_PROBLEMATIC_FROM_NON_OPERATIONAL(989, AuditLogTimeInterval.MINUTE.getValue()),
    SYSTEM_MASTER_DOMAIN_NOT_IN_SYNC(990, AuditLogTimeInterval.MINUTE.getValue()),
    RECOVERY_STORAGE_POOL(991),
    RECOVERY_STORAGE_POOL_FAILED(992),
    SYSTEM_CHANGE_STORAGE_POOL_STATUS_RESET_IRS(993, AuditLogTimeInterval.MINUTE.getValue()),
    CONNECT_STORAGE_SERVERS_FAILED(994),
    CONNECT_STORAGE_POOL_FAILED(995),
    STORAGE_DOMAIN_ERROR(996),
    REFRESH_REPOSITORY_IMAGE_LIST_FAILED(997),
    REFRESH_REPOSITORY_IMAGE_LIST_SUCCEEDED(998),
    STORAGE_ALERT_VG_METADATA_CRITICALLY_FULL(999, AuditLogTimeInterval.MINUTE.getValue() * 15),
    STORAGE_ALERT_SMALL_VG_METADATA(1000, AuditLogTimeInterval.HOUR.getValue() * 12),
    USER_ATTACH_STORAGE_DOMAINS_TO_POOL(1002),
    USER_ATTACH_STORAGE_DOMAINS_TO_POOL_FAILED(1003),
    STORAGE_DOMAIN_TASKS_ERROR(1004),
    UPDATE_OVF_FOR_STORAGE_POOL_FAILED(1005),
    UPGRADE_STORAGE_POOL_ENCOUNTERED_PROBLEMS(1006),
    REFRESH_REPOSITORY_IMAGE_LIST_INCOMPLETE(1007),

    RELOAD_CONFIGURATIONS_SUCCESS(1010),
    RELOAD_CONFIGURATIONS_FAILURE(1011),

    // Authentication
    USER_ACCOUNT_DISABLED_OR_LOCKED(1100, AuditLogTimeInterval.HOUR.getValue()),
    USER_ACCOUNT_PASSWORD_EXPIRED(1101, AuditLogTimeInterval.HOUR.getValue()),

    // Providers
    PROVIDER_ADDED(1150),
    PROVIDER_ADDITION_FAILED(1151),
    PROVIDER_UPDATED(1152),
    PROVIDER_UPDATE_FAILED(1153),
    PROVIDER_REMOVED(1154),
    PROVIDER_REMOVAL_FAILED(1155),
    PROVIDER_CERTIFICATE_CHAIN_IMPORTED(1156),
    PROVIDER_CERTIFICATE_CHAIN_IMPORT_FAILED(1157),

    // General
    ENTITY_RENAMED(1200),

    VDS_ALERT_FENCE_IS_NOT_CONFIGURED(9000),
    VDS_ALERT_FENCE_TEST_FAILED(9001),
    VDS_ALERT_FENCE_OPERATION_FAILED(9002),
    VDS_ALERT_FENCE_OPERATION_SKIPPED(9003),
    VDS_ALERT_FENCE_NO_PROXY_HOST(9004),
    VDS_ALERT_FENCE_STATUS_VERIFICATION_FAILED(9005),
    CANNOT_HIBERNATE_RUNNING_VMS_AFTER_CLUSTER_CPU_UPGRADE(9006),
    VDS_ALERT_SECONDARY_AGENT_USED_FOR_FENCE_OPERATION(9007),

    TASK_STOPPING_ASYNC_TASK(9500, AuditLogTimeInterval.MINUTE.getValue()),
    TASK_CLEARING_ASYNC_TASK(9501, AuditLogTimeInterval.MINUTE.getValue()),

    VDS_ACTIVATE_ASYNC(9502, AuditLogTimeInterval.HOUR.getValue() * 3), // When VDS is reactivated by autorecovery
    VDS_ACTIVATE_FAILED_ASYNC(9503, AuditLogTimeInterval.HOUR.getValue() * 3), // When VDS is reactivated
    STORAGE_ACTIVATE_ASYNC(9504, AuditLogTimeInterval.HOUR.getValue() * 3), // When VDS is reactivated by autorecovery

    USER_ACTIVATED_STORAGE_DOMAIN_ASYNC(9505, AuditLogTimeInterval.HOUR.getValue() * 3),
    USER_ACTIVATE_STORAGE_DOMAIN_FAILED_ASYNC(9506, AuditLogTimeInterval.HOUR.getValue() * 3),

    IMPORTEXPORT_IMPORT_VM_INVALID_INTERFACES(9600, AuditLogTimeInterval.MINUTE.getValue()),
    VDS_SET_NON_OPERATIONAL_VM_NETWORK_IS_BRIDGELESS(9601, AuditLogTimeInterval.MINUTE.getValue()),

    EMULATED_MACHINES_INCOMPATIBLE_WITH_CLUSTER(9604, AuditLogTimeInterval.MINUTE.getValue()),

    /** Highly available virtual machine went down. */
    HA_VM_FAILED(9602),
    /** Restart of a highly available virtual machine failed. */
    HA_VM_RESTART_FAILED(9603),
    EXCEEDED_MAXIMUM_NUM_OF_RESTART_HA_VM_ATTEMPTS(9605),

    IMPORTEXPORT_SNAPSHOT_VM_INVALID_INTERFACES(9606, AuditLogTimeInterval.MINUTE.getValue()),
    ADD_VM_FROM_SNAPSHOT_INVALID_INTERFACES(9607, AuditLogTimeInterval.MINUTE.getValue()),

    // DWH
    DWH_STOPPED(9701),
    DWH_STARTED(9700),
    DWH_ERROR(9704),

    // External Events/Alerts
    EXTERNAL_EVENT_NORMAL(9801),
    EXTERNAL_EVENT_WARNING(9802),
    EXTERNAL_EVENT_ERROR(9803),
    EXTERNAL_ALERT(9804),

    //watchdog
    WATCHDOG_EVENT(9901),

    // cluster policy
    USER_ADD_CLUSTER_POLICY(9910),
    USER_FAILED_TO_ADD_CLUSTER_POLICY(9911),
    USER_UPDATE_CLUSTER_POLICY(9912),
    USER_FAILED_TO_UPDATE_CLUSTER_POLICY(9913),
    USER_REMOVE_CLUSTER_POLICY(9914),
    USER_FAILED_TO_REMOVE_CLUSTER_POLICY(9915),

    // external scheduler
    FAILED_TO_CONNECT_TO_SCHEDULER_PROXY(9920, AuditLogTimeInterval.MINUTE.getValue()),

    //trusted service
    VDS_UNTRUSTED(10000, AuditLogTimeInterval.MINUTE.getValue()),
    USER_UPDATE_VM_FROM_TRUSTED_TO_UNTRUSTED(10001),
    USER_UPDATE_VM_FROM_UNTRUSTED_TO_TRUSTED(10002),
    IMPORTEXPORT_IMPORT_VM_FROM_TRUSTED_TO_UNTRUSTED(10003),
    IMPORTEXPORT_IMPORT_VM_FROM_UNTRUSTED_TO_TRUSTED(10004),
    USER_ADD_VM_FROM_TRUSTED_TO_UNTRUSTED(10005),
    USER_ADD_VM_FROM_UNTRUSTED_TO_TRUSTED(10006),
    IMPORTEXPORT_IMPORT_TEMPLATE_FROM_TRUSTED_TO_UNTRUSTED(10007),
    IMPORTEXPORT_IMPORT_TEMPLATE_FROM_UNTRUSTED_TO_TRUSTED(10008),
    USER_ADD_VM_TEMPLATE_FROM_TRUSTED_TO_UNTRUSTED(10009),
    USER_ADD_VM_TEMPLATE_FROM_UNTRUSTED_TO_TRUSTED(10010),
    USER_UPDATE_VM_TEMPLATE_FROM_TRUSTED_TO_UNTRUSTED(10011),
    USER_UPDATE_VM_TEMPLATE_FROM_UNTRUSTED_TO_TRUSTED(10012),

    // External tasks
    USER_ADD_EXTERNAL_JOB(11000),
    USER_ADD_EXTERNAL_JOB_FAILED(11001),

    //network Qos
    USER_ADDED_NETWORK_QOS(10100),
    USER_FAILED_TO_ADD_NETWORK_QOS(10101),
    USER_REMOVED_NETWORK_QOS(10102),
    USER_FAILED_TO_REMOVE_NETWORK_QOS(10103),
    USER_UPDATED_NETWORK_QOS(10104),
    USER_FAILED_TO_UPDATE_NETWORK_QOS(10105),

    //mom policies
    USER_UPDATED_MOM_POLICIES(10200),
    USER_FAILED_TO_UPDATE_MOM_POLICIES(10201),

    //power management policy
    PM_POLICY_UP_TO_MAINTENANCE(10250),
    PM_POLICY_MAINTENANCE_TO_DOWN(10251),
    PM_POLICY_TO_UP(10252),

    // HA Reservation
    CLUSTER_ALERT_HA_RESERVATION(10300, AuditLogTimeInterval.HOUR.getValue()),

    //affinity groups
    USER_ADDED_AFFINITY_GROUP(10350),
    USER_FAILED_TO_ADD_AFFINITY_GROUP(10351),
    USER_UPDATED_AFFINITY_GROUP(10352),
    USER_FAILED_TO_UPDATE_AFFINITY_GROUP(10353),
    USER_REMOVED_AFFINITY_GROUP(10354),
    USER_FAILED_TO_REMOVE_AFFINITY_GROUP(10355),

    // iSCSI bond
    ISCSI_BOND_ADD_SUCCESS(10400),
    ISCSI_BOND_ADD_FAILED(10401),
    ISCSI_BOND_EDIT_SUCCESS(10402),
    ISCSI_BOND_EDIT_FAILED(10403),
    ISCSI_BOND_REMOVE_SUCCESS(10404),
    ISCSI_BOND_REMOVE_FAILED(10405);

    private int intValue;
    // indicates time interval in seconds on which identical events from same instance are suppressed.
    private int eventFloodRate;
    private static final Map<Integer, AuditLogType> mappings = new HashMap<Integer, AuditLogType>();

    static {
        for (AuditLogType logType : values()) {
            mappings.put(logType.getValue(), logType);
        }
    }

    private AuditLogType(int value) {
        this(value, 0);
    }

    private AuditLogType(int value, int eventFloodRate) {
        intValue = value;
        this.eventFloodRate = eventFloodRate;
    }

    public int getValue() {
        return intValue;
    }

    public int getEventFloodRate() {
        return eventFloodRate;
    }

    public static AuditLogType forValue(int value) {
        return mappings.get(value);
    }

}
