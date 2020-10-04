package ir.mine.project;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import ir.mine.project.base.authorization.autoupdate.annot.SecurityModel;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityRole;
import ir.mine.project.base.authorization.autoupdate.annot.SecurityUser;
import ir.mine.project.staticvariable.RoleNames;

@Component
@DependsOn({ "OperationServiceImpl", "RoleServiceImpl", "ProfileServiceImpl", "ApprovalsServiceImpl",
		"HolidayServiceImpl", "MeetingServiceImpl", "MeetingRoomServiceImpl", "RoomServiceServiceImpl",
		"ServiceTypeServiceImpl", "SmsServiceImpl", "SmsConfigServiceImpl", "WorkingHourServiceImpl", })
@SecurityModel(operations = {}, roles = { @SecurityRole(description = "", name = RoleNames.ADMIN, operations = {

		"role:create", "role:read", "role:update", "role:delete", "operation:create", "operation:read",
		"operation:update", "operation:delete",

		"profile:create", "profile:read", "profile:update", "profile:delete", "approvals:create", "approvals:read",
		"approvals:update", "approvals:delete", "holiday:create", "holiday:read", "holiday:update", "holiday:delete",
		"meeting:create", "meeting:read", "meeting:update", "meeting:delete", "meetingroom:create", "meetingroom:read",
		"meetingroom:update", "meetingroom:delete", "roomservice:create", "roomservice:read", "roomservice:update",
		"roomservice:delete", "servicetype:create", "servicetype:read", "servicetype:update", "servicetype:delete",
		"sms:create", "sms:read", "sms:update", "sms:delete", "smsconfig:create", "smsconfig:read", "smsconfig:update",
		"smsconfig:delete", "workinghour:create", "workinghour:read", "workinghour:update", "workinghour:delete",

		}),

		@SecurityRole(description = "", name = RoleNames.SECRATERY, operations = { 
				"role:create", "role:read", "role:update", "role:delete", "operation:create", "operation:read",
				"operation:update", "operation:delete",

				"profile:create", "profile:read", "profile:update", "profile:delete", "approvals:create", "approvals:read",
				"approvals:update", "approvals:delete", "holiday:create", "holiday:read", "holiday:update", "holiday:delete",
				"meeting:create", "meeting:read", "meeting:update", "meeting:delete", "meetingroom:create", "meetingroom:read",
				"meetingroom:update", "meetingroom:delete", "roomservice:create", "roomservice:read", "roomservice:update",
				"roomservice:delete", "servicetype:create", "servicetype:read", "servicetype:update", "servicetype:delete",
				"sms:create", "sms:read", "sms:update", "sms:delete", "smsconfig:create", "smsconfig:read", "smsconfig:update",
				"smsconfig:delete", "workinghour:create", "workinghour:read", "workinghour:update", "workinghour:delete",
		}),

		@SecurityRole(description = "", name = RoleNames.INVITEES, operations = { "role:read", "operation:read",
				"profile:read", "profile:update", "approvals:read", "meeting:read", "meetingroom:read",
				"workinghour:read", "holiday:read" }),

}, users = { @SecurityUser(password = "admin", roles = { "ADMIN" }, username = "admin", superUser = true), })

public class UserAutoDeployer {
}
