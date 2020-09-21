'use strict';
app
	.factory(
		'Labels',
		[function () {
			return {
				app: "سامانه مدیریت جلسات",
				Generals: {
					detailView: "جزئیات",
					addMeeing: "افزودن جلسه",
					detailMeeting: "مشاهده جزئیات جلسه",
					addHoliday: "افزودن تعطیلات",
					detailHoliday: "مشاهده جزئیات تعطیلات",
					addRoom: "افزودن مکان جلسه",
					detailRoom: "مشاهده جزئیات مکان جلسه ",
					addService: "افزودن امکانات",
					detailService: "مشاهده جزئیات امکانات",
					detailWorkingHour: "مشاهده جزئیات ساعت کاری",
					addProfile: "افزودن اطلاعات شخصی",
					detailProfile: "مشاهده جزئیات اطلاعات شخصی",
					list: "فهرست",
					add: "ایجاد",
					edit: "ویرایش",
					rowsInPage: "تعداد سطر",
					selectPage: "انتخاب صفحه",
					totalRecords: "تعداد کل",
					fire: "اعمال",
					report: "گزارش",
					search: "جستجوی",
					selectedItems: "موارد انتخابی",
					radif: "#",
					operations: "اقدامات",
					operation: "عملیات"
				},


				Menu: {
					main: "فهرست",
					home: "خانه",
					logout: "خروج",
					account: "حساب کاربری",
					profile: "اطلاعات شخصی",
					profiles: "کاربران",
					changePassword: "تغییر کلمه عبور",
					approvals: "مصوبه",
					holidaie: "تعطیلات",
					meeting: "جلسه",
					meetingroom: "مکان جلسه",
					servicetype: "امکانات اتاق",
					sms: "پیامک",
					smsconfig: "تنظیمات پیامک",
					workinghour: "ساعت کاری",
					baseTables: "جدول پایه",
					meetingCalendar: "تقویم جلسات",
					present: "حضور و غیاب",
					detailMeeting: "مشخصات جلسه"

				},

				Buttons: {
					save: "ذخیره",
					cancel: "لغو",
					New: "جدید",
					changePassword: "تغییر کلمه عبور",
					download: "دریافت",
					clear: "پاک کردن",
					print: "چاپ",
					reprint: "چاپ مجدد",
					downloadAttachment: "دریافت ضمیمه",
					showDetail: "نمایش جزئیات",
					search: "جستجو",
					chooseImage: "انتخاب عکس",
					chooseFile: "انتخاب فایل",
					edit: "ویرایش",
					deleted: "حذف",
					deleteFile: "حذف فایل",
					deleteImage: "حذف تصویر",
					conformityCheck: "بررسی تطابق",
					approvalConfirm: "ثبت مصوبه",
					rejected: "رد ",
					nextPage: "صفحه بعدی",
					backPage: "صفحه قبلی"

				},
				Success: {
					save: "ذخیره سازی با موفقیت انجام شد",
					update: "بروزرسانی با موفقیت انجام شد",
					deleted: "موجودیت مورد نظر با موفقیت حذف شد",
					operationDone: "عملیات با موفقیت انجام شد",
					reject: "جلسه مورد نظر با موفقیت لغو شد"

				},
				Info: {},
				Warning: {
					fillForm: "لطفا فیلد های اجباری را پر نمایید",
					dataNotFound: "نتیجه ای برای جستجو یافت نشد",
					correctlyFild: "لطفا فیلد ها رو درست وارد کنید",
					meetingIsBooked: "جلسه در این زمان رزرو شده است",
					dayIsHoliday: "این روز تعطیل می باشد",
					meetingIsEmpty: "جلسه ای ذخیره نشده است",
					dataRedantant: "داده تکراری قابل قبول نیست",
					characterNotSuccess : "رمز عبور شما باید بیشتر از هشت کاراکتر باشد"

				},
				Error: {
					accessDeniedTitle: "خطای دسترسی",
					accessDeniedContent: "با عرض پوزش حساب کاربری شما به صفحه مورد نظر دسترسی ندارد",
					currentPassword: "رمز عبور فعلی صحیح نمی باشد",
					notEqualPassword: "رمز عبور و تکرار آن تطابق ندارند",
				},

				ChangePassword: {
					main: "تغییر کلمه عبور",
					currentPassword: "رمز عبور فعلی",
					newPassword: "رمز عبور جدید",
					confirmNewPassword: "تکرار رمز عبور جدید"
				},

				Home: {
					uploadDocument: "بارگذاری مستند",
					newFeatures: "نیازمندی جدید",
					uploadVersion: "بارگذاری نسخه",

					downloadVersion: "دریافت نسخه جدید",
					downloadDocument: "دریفات مستند جدید",

					profiles: "مدیریت کاربران",
					projects: "مدیریت پروژه ها",
					organization: "مدیریت سازمان ها"
				},

				Profile: {
					main: "اطلاعات شخصی",
					firstName: "نام",
					lastName: "نام خانوادگی",
					userName: "نام کاربری",
					email: "پست الکترونیکی",
					phoneNumber: "شماره تلفن",
					post: "سمت",
					password: "گذرواژه عبور",
					confirmPassword: "تکرار گذرواژه عبور",
					isActive: "وضعیت",
					expireDate: "تاریخ انتقاضا",
					mobileNumber: "شماره همراه",
					organization: "سازمان",
					profileType: "نوع کاربر"
				},

				ProfileType: {
					admin: "مدیر سیستم",
					secratery: "دبیر",
					invitees: "مدعو",
				},
				Agenda: {
					ruls: "دستور جلسه",
					approvals: "مصوبه"
				},

				Approvals: {
					main: "مصوبه",
					approval: "مصوبات",
					approvals: "مصوبه",
					outOfApproval: "مصوبات خارج از دستور",
					rules: "دستورات",
					createDate: "تاریخ ایجاد",
					startTime: "زمان شروع جلسه",
					endTime: "زمان پایان جلسه",
					meeting: "جلسه",
					approvalFile: "فایل مصوبه",
					presents: "حاضرین",
					absents: "غایبین",
					newOutOfApproval: "مصوبات خارج از دستور جدید"
				},
				Holiday: {
					main: "تعطیلات",
					holidayDate: "تاریخ تعطیلات",
					description: "توضیحات",
				},
				Meeting: {
					main: "جلسه",
					title: "عنوان",
					createDate: "تاریخ ایجاد",
					lastModifiedDate: "تاریخ آخرین تغییرات",
					meetingDate: "تاریخ جلسه",
					startTime: "ساعت شروع",
					endTime: "ساعت پایان",
					meetingStatus: "وضعیت جلسه",
					meetingType: "نوع جلسه",
					meetingPosition: "شرایط جلسه",
					meetingService: "خدمات",
					approvals: "مصوبه",
					meetingRoom: "مکان جلسه",
					creator: "منشی",
					Secretary: "دبیر جلسه",
					boss: "رئیس جلسه",
					invitees: "مدعوین",
					agenda: "دستور جلسه",
					newAgenda: "دستور جلسه جدید",
					nextWeek: "هفته بعد",
					backWeek: "هفته قبل"
				},
				MeetingRoom: {
					main: "مکان جلسه",
					roomName: "نام اتاق جلسه",
					capacity: "ظرفیت",
					address: "مکان برگزاری",
					meeting: "جلسه",
					service: "امکانات",
					expireDate: "تاریخ انقضا"

				},
				ServiceType: {
					main: "امکانات",
					title: "عنوان",
					roomService: "خدمات اتاق",
					serviceNumber: "تعداد",
					expireDate: "تاریخ انقضا"
				},
				Sms: {
					main: "پیامک",
					description: "توضیحات",
					receiverNumber: "شماره گیرنده",
					smsStatus: "وضعیت پیامک",
				},
				SmsConfig: {
					main: "تنظیمات پیامک",
					ipAddress: "آدرس آی پی",
					ipPort: "آی پی پرت",
					merchantId: "شماره بازرگانی",
				},
				WorkingHour: {
					main: "ساعت کاری",
					workDay: "روز کاری",
					startTime: "زمان شروع",
					endTime: "زمان پایان",
				},
				MeetingStatus: {
					main: "وضعیت جلسه",
					NEW: "جدید",
					CONFIRMED: "تایید شده",
					REJECT: "رد شده",
				},
				MeetingType: {
					main: "نوع جلسه",
					MEETING: "جلسه",
					APPOINMENT: "قرار",
				},
				MeetingPosition: {
					main: "شرایط جلسه",
					HOST: "میزبان",
					GUEST: "میهمان",
				},
				SmsStatus: {
					main: "وضعیت پیامک",
					NEW: "جدید",
					SENT: "فرستاده شد",
					FAILED: "رد شد",
				},
				WeekDays: {
					main: "روزهای هفته",
					SATURDAY: "شنبه",
					SUNDAY: "یک شنبه",
					MONDAY: "دوشنبه",
					TUESDAY: "سه شنبه",
					WEDNESDAY: "چهارشنبه",
					THURSDAY: "پنج شنبه",
					FRIDAY: "جمعه",
				},
				MeetingCalendar: {
					main: "تقویم جلسات",
					day: "روز",
					date: "تاریخ",
					cells: "سلول ها",
				},
				Cell: {
					main: "سلول",
					title: "عنوان",
					isHoliday: "تعطیل هست ؟",
					isMeeting: "جلسه دارد ؟",
				}

			}

		}]);