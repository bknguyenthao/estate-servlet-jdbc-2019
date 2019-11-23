<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Chỉnh sửa tòa nhà</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a
						href="<c:url value="/admin-home"/>">Trang chủ</a></li>
					<li><a href="<c:url value="/admin-building?action=LIST"/>">Danh
							sách sản phẩm</a></li>
					<li><a href="#">Chỉnh sửa sản phẩm</a></li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<div class="widget-body">
							<div class="widget-main">
								<div class="form-horizontal">
									<form id="formEdit">
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Tên sản phẩm </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="name" value="${buildingedit.name}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Diện tích sàn </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name=buildingArea value="${buildingedit.buildingArea}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Quận </b></label>
											<div class="col-sm-3">
												<select class="form-control" name="district">
													<option value="">-- Chọn quận --</option>
													<c:forEach var="item" items="${districts}">
														<option value="${item.key}"
															${item.key == buildingedit.district ? 'selected' : ''}>${item.value}</option>
													</c:forEach>
												</select>
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Phường </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="ward" value="${buildingedit.ward}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Đường </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="street" value="${buildingedit.street}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Hướng </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="direction" value="${buildingedit.direction}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Hạng </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="level" value="${buildingedit.level}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Số tầng hầm </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="numberOfBasement"
													value="${buildingedit.numberOfBasement}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Diện tích thuê </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="rentArea" value="${buildingedit.rentArea}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Giá thuê </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="costRent" value="${buildingedit.costRent}">
											</div>
										</div>

										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Tên quản lý </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="managerName" value="${buildingedit.managerName}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Số điện thoại quản lý </b></label>
											<div class="col-sm-10">
												<input type="text" id="form-field-1-1" class="form-control"
													name="managerPhone" value="${buildingedit.managerPhone}">
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Loại tòa nhà </b></label>
											<div class="col-xs-12 col-sm-5">
												<div class="control-group">
													<div class="fg-line">
														<c:forEach var="item" items="${buildingtypes}">
															<label class="checkbox-inline"> <input
																type="checkbox" value="${item.key}" name="buildingType"
																${fn:contains(fn:join(buildingedit.buildingTypes,','), item.key) ? 'checked' : ''}>
																${item.value}
															</label>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
										<div class="form-group">
											<label class="col-sm-2 control-label no-padding-right"
												for="form-field-1-1"><b> Upload hình ảnh </b></label>
											<div class="col-xs-10">
												<label class="ace-file-input"><input type="file"
													id="id-input-file-2"><span
													class="ace-file-container" data-title="Choose"><span
														class="ace-file-name" data-title="No File ..."><i
															class=" ace-icon fa fa-upload"></i></span></span><a class="remove"
													href="#"><i class=" ace-icon fa fa-times"></i></a></label>
											</div>
										</div>
										<input type="hidden" name="id" value="${buildingedit.id}"
											id="buildingId" />
									</form>
									<div class="form-group">
										<div class="col-md-offset-2 col-md-10">
											<button class="btn btn-info" type="button" id="btnEdit">
												<i class="ace-icon fa fa-check bigger-110"></i> Chỉnh sửa
											</button>
											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i> Hủy
											</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- /.main-content -->
	<script type="text/javascript">
		$("#btnEdit").click(function() {
			editBuilding();
		});
		function editBuilding() {
			var formData = $("#formEdit").serializeArray();
			var data = {};
			var buildingTypes = [];
			$.each(formData, function(index, v) {
				if (v.name == 'buildingType') {
					buildingTypes.push(v.value);
				} else {
					data["" + v.name + ""] = v.value;
				}
			});
			data["buildingTypes"] = buildingTypes;
			sendAjaxEdit(data);
		}

		function sendAjaxEdit(data) {
			$
					.ajax({
						type : "PUT",
						url : "<c:url value = '/api-admin-building'/>",
						data : JSON.stringify(data),
						contentType : "application/json",
						dataType : "json",
						crossDomain : true,
						success : function(msg) {
							var buildingId = $("#buildingId").val();
							window.location.href = "<c:url value = '/admin-building?action=EDIT'/>"
									+ "&buildingId=" + buildingId;
							alert("success");

						},
						error : function(request, status, error) {
							window.location.href = "<c:url value = '/admin-building?action=EDIT'/>"
									+ "&buildingId=" + buildingId;
							;
							alert(error);
						}
					});
		}
	</script>
</body>
</html>